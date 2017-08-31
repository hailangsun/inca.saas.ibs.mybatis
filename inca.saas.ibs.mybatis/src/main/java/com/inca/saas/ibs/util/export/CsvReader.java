package com.inca.saas.ibs.util.export;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.HashMap;

public class CsvReader
{
    private class StaticSettings
    {

        public static final int MAX_BUFFER_SIZE = 1024;
        public static final int MAX_FILE_BUFFER_SIZE = 4096;
        public static final int INITIAL_COLUMN_COUNT = 10;
        public static final int INITIAL_COLUMN_BUFFER_SIZE = 50;

        private StaticSettings()
        {
            super();
        }
    }

    private class HeadersHolder
    {

        public String Headers[];
        public int Length;
        public HashMap IndexByName;

        public HeadersHolder()
        {
            super();
            Headers = null;
            Length = 0;
            IndexByName = new HashMap();
        }
    }

    private class UserSettings
    {

        public boolean CaseSensitive;
        public char TextQualifier;
        public boolean TrimWhitespace;
        public boolean UseTextQualifier;
        public char Delimiter;
        public char RecordDelimiter;
        public char Comment;
        public boolean UseComments;
        public int EscapeMode;
        public boolean SafetySwitch;
        public boolean SkipEmptyRecords;
        public boolean CaptureRawRecord;

        public UserSettings()
        {
            super();
            CaseSensitive = true;
            TextQualifier = '"';
            TrimWhitespace = true;
            UseTextQualifier = true;
            Delimiter = ',';
            RecordDelimiter = '\0';
            Comment = '#';
            UseComments = false;
            EscapeMode = 1;
            SafetySwitch = true;
            SkipEmptyRecords = true;
            CaptureRawRecord = true;
        }
    }

    private class Letters
    {

        public static final char LF = 10;
        public static final char CR = 13;
        public static final char QUOTE = 34;
        public static final char COMMA = 44;
        public static final char SPACE = 32;
        public static final char TAB = 9;
        public static final char POUND = 35;
        public static final char BACKSLASH = 92;
        public static final char NULL = 0;
        public static final char BACKSPACE = 8;
        public static final char FORM_FEED = 12;
        public static final char ESCAPE = 27;
        public static final char VERTICAL_TAB = 11;
        public static final char ALERT = 7;

        private Letters()
        {
            super();
        }
    }

    private class RawRecordBuffer
    {

        public char Buffer[];
        public int Position;

        public RawRecordBuffer()
        {
            super();
            Buffer = new char[500];
            Position = 0;
        }
    }

    private class ColumnBuffer
    {

        public char Buffer[];
        public int Position;

        public ColumnBuffer()
        {
            super();
            Buffer = new char[50];
            Position = 0;
        }
    }

    private class DataBuffer
    {

        public char Buffer[];
        public int Position;
        public int Count;
        public int ColumnStart;
        public int LineStart;

        public DataBuffer()
        {
            super();
            Buffer = new char[1024];
            Position = 0;
            Count = 0;
            ColumnStart = 0;
            LineStart = 0;
        }
    }

    private class ComplexEscape
    {

        private static final int UNICODE = 1;
        private static final int OCTAL = 2;
        private static final int DECIMAL = 3;
        private static final int HEX = 4;

        private ComplexEscape()
        {
            super();
        }
    }


    public CsvReader(String s, char c, Charset charset1)
        throws FileNotFoundException
    {
        inputStream = null;
        fileName = null;
        userSettings = new UserSettings();
        charset = null;
        useCustomRecordDelimiter = false;
        dataBuffer = new DataBuffer();
        columnBuffer = new ColumnBuffer();
        rawBuffer = new RawRecordBuffer();
        isQualified = null;
        rawRecord = "";
        headersHolder = new HeadersHolder();
        startedColumn = false;
        startedWithQualifier = false;
        hasMoreData = true;
        lastLetter = '\0';
        hasReadNextLine = false;
        columnsCount = 0;
        currentRecord = 0L;
        values = new String[10];
        initialized = false;
        closed = false;
        if(s == null)
            throw new IllegalArgumentException("Parameter fileName can not be null.");
        if(charset1 == null)
            throw new IllegalArgumentException("Parameter charset can not be null.");
        if(!(new File(s)).exists())
        {
            throw new FileNotFoundException("File " + s + " does not exist.");
        } else
        {
            fileName = s;
            userSettings.Delimiter = c;
            charset = charset1;
            isQualified = new boolean[values.length];
            return;
        }
    }

    public CsvReader(String s, char c)
        throws FileNotFoundException
    {
        this(s, c, Charset.forName("ISO-8859-1"));
    }

    public CsvReader(String s)
        throws FileNotFoundException
    {
        this(s, ',');
    }

    public CsvReader(Reader reader, char c)
    {
        inputStream = null;
        fileName = null;
        userSettings = new UserSettings();
        charset = null;
        useCustomRecordDelimiter = false;
        dataBuffer = new DataBuffer();
        columnBuffer = new ColumnBuffer();
        rawBuffer = new RawRecordBuffer();
        isQualified = null;
        rawRecord = "";
        headersHolder = new HeadersHolder();
        startedColumn = false;
        startedWithQualifier = false;
        hasMoreData = true;
        lastLetter = '\0';
        hasReadNextLine = false;
        columnsCount = 0;
        currentRecord = 0L;
        values = new String[10];
        initialized = false;
        closed = false;
        if(reader == null)
        {
            throw new IllegalArgumentException("Parameter inputStream can not be null.");
        } else
        {
            inputStream = reader;
            userSettings.Delimiter = c;
            initialized = true;
            isQualified = new boolean[values.length];
            return;
        }
    }

    public CsvReader(Reader reader)
    {
        this(reader, ',');
    }

    public CsvReader(InputStream inputstream, char c, Charset charset1)
    {
        this(((Reader) (new InputStreamReader(inputstream, charset1))), c);
    }

    public CsvReader(InputStream inputstream, Charset charset1)
    {
        this(((Reader) (new InputStreamReader(inputstream, charset1))));
    }

    public boolean getCaptureRawRecord()
    {
        return userSettings.CaptureRawRecord;
    }

    public void setCaptureRawRecord(boolean flag)
    {
        userSettings.CaptureRawRecord = flag;
    }

    public String getRawRecord()
    {
        return rawRecord;
    }

    public boolean getTrimWhitespace()
    {
        return userSettings.TrimWhitespace;
    }

    public void setTrimWhitespace(boolean flag)
    {
        userSettings.TrimWhitespace = flag;
    }

    public char getDelimiter()
    {
        return userSettings.Delimiter;
    }

    public void setDelimiter(char c)
    {
        userSettings.Delimiter = c;
    }

    public char getRecordDelimiter()
    {
        return userSettings.RecordDelimiter;
    }

    public void setRecordDelimiter(char c)
    {
        useCustomRecordDelimiter = true;
        userSettings.RecordDelimiter = c;
    }

    public char getTextQualifier()
    {
        return userSettings.TextQualifier;
    }

    public void setTextQualifier(char c)
    {
        userSettings.TextQualifier = c;
    }

    public boolean getUseTextQualifier()
    {
        return userSettings.UseTextQualifier;
    }

    public void setUseTextQualifier(boolean flag)
    {
        userSettings.UseTextQualifier = flag;
    }

    public char getComment()
    {
        return userSettings.Comment;
    }

    public void setComment(char c)
    {
        userSettings.Comment = c;
    }

    public boolean getUseComments()
    {
        return userSettings.UseComments;
    }

    public void setUseComments(boolean flag)
    {
        userSettings.UseComments = flag;
    }

    public int getEscapeMode()
    {
        return userSettings.EscapeMode;
    }

    public void setEscapeMode(int i)
        throws IllegalArgumentException
    {
        if(i != 1 && i != 2)
        {
            throw new IllegalArgumentException("Parameter escapeMode must be a valid value.");
        } else
        {
            userSettings.EscapeMode = i;
            return;
        }
    }

    public boolean getSkipEmptyRecords()
    {
        return userSettings.SkipEmptyRecords;
    }

    public void setSkipEmptyRecords(boolean flag)
    {
        userSettings.SkipEmptyRecords = flag;
    }

    public boolean getSafetySwitch()
    {
        return userSettings.SafetySwitch;
    }

    public void setSafetySwitch(boolean flag)
    {
        userSettings.SafetySwitch = flag;
    }

    public int getColumnCount()
    {
        return columnsCount;
    }

    public long getCurrentRecord()
    {
        return currentRecord - 1L;
    }

    public int getHeaderCount()
    {
        return headersHolder.Length;
    }

    public String[] getHeaders()
        throws IOException
    {
        checkClosed();
        if(headersHolder.Headers == null)
        {
            return null;
        } else
        {
            String as[] = new String[headersHolder.Length];
            System.arraycopy(headersHolder.Headers, 0, as, 0, headersHolder.Length);
            return as;
        }
    }

    public void setHeaders(String as[])
    {
        headersHolder.Headers = as;
        headersHolder.IndexByName.clear();
        if(as != null)
            headersHolder.Length = as.length;
        else
            headersHolder.Length = 0;
        for(int i = 0; i < headersHolder.Length; i++)
            headersHolder.IndexByName.put(as[i], new Integer(i));

    }

    public String[] getValues()
        throws IOException
    {
        checkClosed();
        String as[] = new String[columnsCount];
        System.arraycopy(values, 0, as, 0, columnsCount);
        return as;
    }

    public String get(int i)
        throws IOException
    {
        checkClosed();
        if(i > -1 && i < columnsCount)
            return values[i];
        else
            return "";
    }

    public String get(String s)
        throws IOException
    {
        checkClosed();
        return get(getIndex(s));
    }

    public static CsvReader parse(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("Parameter data can not be null.");
        else
            return new CsvReader(new StringReader(s));
    }

    public boolean readRecord()
        throws IOException
    {
        checkClosed();
        columnsCount = 0;
        rawBuffer.Position = 0;
        dataBuffer.LineStart = dataBuffer.Position;
        hasReadNextLine = false;
        if(hasMoreData)
        {
            do
                if(dataBuffer.Position == dataBuffer.Count)
                {
                    checkDataLength();
                } else
                {
                    startedWithQualifier = false;
                    char c = dataBuffer.Buffer[dataBuffer.Position];
                    if(userSettings.UseTextQualifier && c == userSettings.TextQualifier)
                    {
                        lastLetter = c;
                        startedColumn = true;
                        dataBuffer.ColumnStart = dataBuffer.Position + 1;
                        startedWithQualifier = true;
                        boolean flag = false;
                        char c1 = userSettings.TextQualifier;
                        if(userSettings.EscapeMode == 2)
                            c1 = '\\';
                        boolean flag3 = false;
                        boolean flag4 = false;
                        boolean flag5 = false;
                        int k = 1;
                        int l = 0;
                        char c3 = '\0';
                        dataBuffer.Position++;
                        do
                            if(dataBuffer.Position == dataBuffer.Count)
                            {
                                checkDataLength();
                            } else
                            {
                                c = dataBuffer.Buffer[dataBuffer.Position];
                                if(flag3)
                                {
                                    dataBuffer.ColumnStart = dataBuffer.Position + 1;
                                    if(c == userSettings.Delimiter)
                                        endColumn();
                                    else
                                    if(!useCustomRecordDelimiter && (c == '\r' || c == '\n') || useCustomRecordDelimiter && c == userSettings.RecordDelimiter)
                                    {
                                        endColumn();
                                        endRecord();
                                    }
                                } else
                                if(flag5)
                                {
                                    l++;
                                    switch(k)
                                    {
                                    case 1: // '\001'
                                        c3 *= '\020';
                                        c3 += hexToDec(c);
                                        if(l == 4)
                                            flag5 = false;
                                        break;

                                    case 2: // '\002'
                                        c3 *= '\b';
                                        c3 += (char)(c - 48);
                                        if(l == 3)
                                            flag5 = false;
                                        break;

                                    case 3: // '\003'
                                        c3 *= '\n';
                                        c3 += (char)(c - 48);
                                        if(l == 3)
                                            flag5 = false;
                                        break;

                                    case 4: // '\004'
                                        c3 *= '\020';
                                        c3 += hexToDec(c);
                                        if(l == 2)
                                            flag5 = false;
                                        break;
                                    }
                                    if(!flag5)
                                        appendLetter(c3);
                                    else
                                        dataBuffer.ColumnStart = dataBuffer.Position + 1;
                                } else
                                if(c == userSettings.TextQualifier)
                                {
                                    if(flag4)
                                    {
                                        flag4 = false;
                                        flag = false;
                                    } else
                                    {
                                        updateCurrentValue();
                                        if(userSettings.EscapeMode == 1)
                                            flag4 = true;
                                        flag = true;
                                    }
                                } else
                                if(userSettings.EscapeMode == 2 && flag4)
                                {
                                    switch(c)
                                    {
                                    case 56: // '8'
                                    case 57: // '9'
                                    case 58: // ':'
                                    case 59: // ';'
                                    case 60: // '<'
                                    case 61: // '='
                                    case 62: // '>'
                                    case 63: // '?'
                                    case 64: // '@'
                                    case 65: // 'A'
                                    case 66: // 'B'
                                    case 67: // 'C'
                                    case 69: // 'E'
                                    case 70: // 'F'
                                    case 71: // 'G'
                                    case 72: // 'H'
                                    case 73: // 'I'
                                    case 74: // 'J'
                                    case 75: // 'K'
                                    case 76: // 'L'
                                    case 77: // 'M'
                                    case 78: // 'N'
                                    case 80: // 'P'
                                    case 81: // 'Q'
                                    case 82: // 'R'
                                    case 83: // 'S'
                                    case 84: // 'T'
                                    case 86: // 'V'
                                    case 87: // 'W'
                                    case 89: // 'Y'
                                    case 90: // 'Z'
                                    case 91: // '['
                                    case 92: // '\\'
                                    case 93: // ']'
                                    case 94: // '^'
                                    case 95: // '_'
                                    case 96: // '`'
                                    case 99: // 'c'
                                    case 103: // 'g'
                                    case 104: // 'h'
                                    case 105: // 'i'
                                    case 106: // 'j'
                                    case 107: // 'k'
                                    case 108: // 'l'
                                    case 109: // 'm'
                                    case 112: // 'p'
                                    case 113: // 'q'
                                    case 115: // 's'
                                    case 119: // 'w'
                                    default:
                                        break;

                                    case 110: // 'n'
                                        appendLetter('\n');
                                        break;

                                    case 114: // 'r'
                                        appendLetter('\r');
                                        break;

                                    case 116: // 't'
                                        appendLetter('\t');
                                        break;

                                    case 98: // 'b'
                                        appendLetter('\b');
                                        break;

                                    case 102: // 'f'
                                        appendLetter('\f');
                                        break;

                                    case 101: // 'e'
                                        appendLetter('\033');
                                        break;

                                    case 118: // 'v'
                                        appendLetter('\013');
                                        break;

                                    case 97: // 'a'
                                        appendLetter('\007');
                                        break;

                                    case 48: // '0'
                                    case 49: // '1'
                                    case 50: // '2'
                                    case 51: // '3'
                                    case 52: // '4'
                                    case 53: // '5'
                                    case 54: // '6'
                                    case 55: // '7'
                                        k = 2;
                                        flag5 = true;
                                        l = 1;
                                        c3 = (char)(c - 48);
                                        dataBuffer.ColumnStart = dataBuffer.Position + 1;
                                        break;

                                    case 68: // 'D'
                                    case 79: // 'O'
                                    case 85: // 'U'
                                    case 88: // 'X'
                                    case 100: // 'd'
                                    case 111: // 'o'
                                    case 117: // 'u'
                                    case 120: // 'x'
                                        switch(c)
                                        {
                                        case 85: // 'U'
                                        case 117: // 'u'
                                            k = 1;
                                            break;

                                        case 88: // 'X'
                                        case 120: // 'x'
                                            k = 4;
                                            break;

                                        case 79: // 'O'
                                        case 111: // 'o'
                                            k = 2;
                                            break;

                                        case 68: // 'D'
                                        case 100: // 'd'
                                            k = 3;
                                            break;
                                        }
                                        flag5 = true;
                                        l = 0;
                                        c3 = '\0';
                                        dataBuffer.ColumnStart = dataBuffer.Position + 1;
                                        break;
                                    }
                                    flag4 = false;
                                } else
                                if(c == c1)
                                {
                                    updateCurrentValue();
                                    flag4 = true;
                                } else
                                if(flag)
                                {
                                    if(c == userSettings.Delimiter)
                                        endColumn();
                                    else
                                    if(!useCustomRecordDelimiter && (c == '\r' || c == '\n') || useCustomRecordDelimiter && c == userSettings.RecordDelimiter)
                                    {
                                        endColumn();
                                        endRecord();
                                    } else
                                    {
                                        dataBuffer.ColumnStart = dataBuffer.Position + 1;
                                        flag3 = true;
                                    }
                                    flag = false;
                                }
                                lastLetter = c;
                                if(startedColumn)
                                {
                                    dataBuffer.Position++;
                                    if(userSettings.SafetySwitch && (dataBuffer.Position - dataBuffer.ColumnStart) + columnBuffer.Position > 100000)
                                    {
                                        close();
                                        throw new IOException("Maximum column length of 100,000 exceeded in column " + NumberFormat.getIntegerInstance().format(columnsCount) + " in record " + NumberFormat.getIntegerInstance().format(currentRecord) + ". Set the SafetySwitch property to false" + " if you're expecting column lengths greater than 100,000 characters to" + " avoid this error.");
                                    }
                                }
                            }
                        while(hasMoreData && startedColumn);
                    } else
                    if(c == userSettings.Delimiter)
                    {
                        lastLetter = c;
                        endColumn();
                    } else
                    if(useCustomRecordDelimiter && c == userSettings.RecordDelimiter)
                    {
                        if(startedColumn || columnsCount > 0 || !userSettings.SkipEmptyRecords)
                        {
                            endColumn();
                            endRecord();
                        } else
                        {
                            dataBuffer.LineStart = dataBuffer.Position + 1;
                        }
                        lastLetter = c;
                    } else
                    if(!useCustomRecordDelimiter && (c == '\r' || c == '\n'))
                    {
                        if(startedColumn || columnsCount > 0 || !userSettings.SkipEmptyRecords && (c == '\r' || lastLetter != '\r'))
                        {
                            endColumn();
                            endRecord();
                        } else
                        {
                            dataBuffer.LineStart = dataBuffer.Position + 1;
                        }
                        lastLetter = c;
                    } else
                    if(userSettings.UseComments && columnsCount == 0 && c == userSettings.Comment)
                    {
                        lastLetter = c;
                        skipLine();
                    } else
                    if(userSettings.TrimWhitespace && (c == ' ' || c == '\t'))
                    {
                        startedColumn = true;
                        dataBuffer.ColumnStart = dataBuffer.Position + 1;
                    } else
                    {
                        startedColumn = true;
                        dataBuffer.ColumnStart = dataBuffer.Position;
                        boolean flag1 = false;
                        boolean flag2 = false;
                        int i = 1;
                        int j = 0;
                        char c2 = '\0';
                        boolean flag6 = true;
                        do
                            if(!flag6 && dataBuffer.Position == dataBuffer.Count)
                            {
                                checkDataLength();
                            } else
                            {
                                if(!flag6)
                                    c = dataBuffer.Buffer[dataBuffer.Position];
                                if(!userSettings.UseTextQualifier && userSettings.EscapeMode == 2 && c == '\\')
                                {
                                    if(flag1)
                                    {
                                        flag1 = false;
                                    } else
                                    {
                                        updateCurrentValue();
                                        flag1 = true;
                                    }
                                } else
                                if(flag2)
                                {
                                    j++;
                                    switch(i)
                                    {
                                    case 1: // '\001'
                                        c2 *= '\020';
                                        c2 += hexToDec(c);
                                        if(j == 4)
                                            flag2 = false;
                                        break;

                                    case 2: // '\002'
                                        c2 *= '\b';
                                        c2 += (char)(c - 48);
                                        if(j == 3)
                                            flag2 = false;
                                        break;

                                    case 3: // '\003'
                                        c2 *= '\n';
                                        c2 += (char)(c - 48);
                                        if(j == 3)
                                            flag2 = false;
                                        break;

                                    case 4: // '\004'
                                        c2 *= '\020';
                                        c2 += hexToDec(c);
                                        if(j == 2)
                                            flag2 = false;
                                        break;
                                    }
                                    if(!flag2)
                                        appendLetter(c2);
                                    else
                                        dataBuffer.ColumnStart = dataBuffer.Position + 1;
                                } else
                                if(userSettings.EscapeMode == 2 && flag1)
                                {
                                    switch(c)
                                    {
                                    case 56: // '8'
                                    case 57: // '9'
                                    case 58: // ':'
                                    case 59: // ';'
                                    case 60: // '<'
                                    case 61: // '='
                                    case 62: // '>'
                                    case 63: // '?'
                                    case 64: // '@'
                                    case 65: // 'A'
                                    case 66: // 'B'
                                    case 67: // 'C'
                                    case 69: // 'E'
                                    case 70: // 'F'
                                    case 71: // 'G'
                                    case 72: // 'H'
                                    case 73: // 'I'
                                    case 74: // 'J'
                                    case 75: // 'K'
                                    case 76: // 'L'
                                    case 77: // 'M'
                                    case 78: // 'N'
                                    case 80: // 'P'
                                    case 81: // 'Q'
                                    case 82: // 'R'
                                    case 83: // 'S'
                                    case 84: // 'T'
                                    case 86: // 'V'
                                    case 87: // 'W'
                                    case 89: // 'Y'
                                    case 90: // 'Z'
                                    case 91: // '['
                                    case 92: // '\\'
                                    case 93: // ']'
                                    case 94: // '^'
                                    case 95: // '_'
                                    case 96: // '`'
                                    case 99: // 'c'
                                    case 103: // 'g'
                                    case 104: // 'h'
                                    case 105: // 'i'
                                    case 106: // 'j'
                                    case 107: // 'k'
                                    case 108: // 'l'
                                    case 109: // 'm'
                                    case 112: // 'p'
                                    case 113: // 'q'
                                    case 115: // 's'
                                    case 119: // 'w'
                                    default:
                                        break;

                                    case 110: // 'n'
                                        appendLetter('\n');
                                        break;

                                    case 114: // 'r'
                                        appendLetter('\r');
                                        break;

                                    case 116: // 't'
                                        appendLetter('\t');
                                        break;

                                    case 98: // 'b'
                                        appendLetter('\b');
                                        break;

                                    case 102: // 'f'
                                        appendLetter('\f');
                                        break;

                                    case 101: // 'e'
                                        appendLetter('\033');
                                        break;

                                    case 118: // 'v'
                                        appendLetter('\013');
                                        break;

                                    case 97: // 'a'
                                        appendLetter('\007');
                                        break;

                                    case 48: // '0'
                                    case 49: // '1'
                                    case 50: // '2'
                                    case 51: // '3'
                                    case 52: // '4'
                                    case 53: // '5'
                                    case 54: // '6'
                                    case 55: // '7'
                                        i = 2;
                                        flag2 = true;
                                        j = 1;
                                        c2 = (char)(c - 48);
                                        dataBuffer.ColumnStart = dataBuffer.Position + 1;
                                        break;

                                    case 68: // 'D'
                                    case 79: // 'O'
                                    case 85: // 'U'
                                    case 88: // 'X'
                                    case 100: // 'd'
                                    case 111: // 'o'
                                    case 117: // 'u'
                                    case 120: // 'x'
                                        switch(c)
                                        {
                                        case 85: // 'U'
                                        case 117: // 'u'
                                            i = 1;
                                            break;

                                        case 88: // 'X'
                                        case 120: // 'x'
                                            i = 4;
                                            break;

                                        case 79: // 'O'
                                        case 111: // 'o'
                                            i = 2;
                                            break;

                                        case 68: // 'D'
                                        case 100: // 'd'
                                            i = 3;
                                            break;
                                        }
                                        flag2 = true;
                                        j = 0;
                                        c2 = '\0';
                                        dataBuffer.ColumnStart = dataBuffer.Position + 1;
                                        break;
                                    }
                                    flag1 = false;
                                } else
                                if(c == userSettings.Delimiter)
                                    endColumn();
                                else
                                if(!useCustomRecordDelimiter && (c == '\r' || c == '\n') || useCustomRecordDelimiter && c == userSettings.RecordDelimiter)
                                {
                                    endColumn();
                                    endRecord();
                                }
                                lastLetter = c;
                                flag6 = false;
                                if(startedColumn)
                                {
                                    dataBuffer.Position++;
                                    if(userSettings.SafetySwitch && (dataBuffer.Position - dataBuffer.ColumnStart) + columnBuffer.Position > 100000)
                                    {
                                        close();
                                        throw new IOException("Maximum column length of 100,000 exceeded in column " + NumberFormat.getIntegerInstance().format(columnsCount) + " in record " + NumberFormat.getIntegerInstance().format(currentRecord) + ". Set the SafetySwitch property to false" + " if you're expecting column lengths greater than 100,000 characters to" + " avoid this error.");
                                    }
                                }
                            }
                        while(hasMoreData && startedColumn);
                    }
                    if(hasMoreData)
                        dataBuffer.Position++;
                }
            while(hasMoreData && !hasReadNextLine);
            if(startedColumn || lastLetter == userSettings.Delimiter)
            {
                endColumn();
                endRecord();
            }
        }
        if(userSettings.CaptureRawRecord)
        {
            if(hasMoreData)
            {
                if(rawBuffer.Position == 0)
                    rawRecord = new String(dataBuffer.Buffer, dataBuffer.LineStart, dataBuffer.Position - dataBuffer.LineStart - 1);
                else
                    rawRecord = new String(rawBuffer.Buffer, 0, rawBuffer.Position) + new String(dataBuffer.Buffer, dataBuffer.LineStart, dataBuffer.Position - dataBuffer.LineStart - 1);
            } else
            {
                rawRecord = new String(rawBuffer.Buffer, 0, rawBuffer.Position);
            }
        } else
        {
            rawRecord = "";
        }
        return hasReadNextLine;
    }

    private void checkDataLength()
        throws IOException
    {
        if(!initialized)
        {
            if(fileName != null)
                inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charset), 4096);
            charset = null;
            initialized = true;
        }
        updateCurrentValue();
        if(userSettings.CaptureRawRecord && dataBuffer.Count > 0)
        {
            if(rawBuffer.Buffer.length - rawBuffer.Position < dataBuffer.Count - dataBuffer.LineStart)
            {
                int i = rawBuffer.Buffer.length + Math.max(dataBuffer.Count - dataBuffer.LineStart, rawBuffer.Buffer.length);
                char ac[] = new char[i];
                System.arraycopy(rawBuffer.Buffer, 0, ac, 0, rawBuffer.Position);
                rawBuffer.Buffer = ac;
            }
            System.arraycopy(dataBuffer.Buffer, dataBuffer.LineStart, rawBuffer.Buffer, rawBuffer.Position, dataBuffer.Count - dataBuffer.LineStart);
            rawBuffer.Position += dataBuffer.Count - dataBuffer.LineStart;
        }
        try
        {
            dataBuffer.Count = inputStream.read(dataBuffer.Buffer, 0, dataBuffer.Buffer.length);
        }
        catch(IOException ioexception)
        {
            close();
            throw ioexception;
        }
        if(dataBuffer.Count == -1)
            hasMoreData = false;
        dataBuffer.Position = 0;
        dataBuffer.LineStart = 0;
        dataBuffer.ColumnStart = 0;
    }

    public boolean readHeaders()
        throws IOException
    {
        boolean flag = readRecord();
        headersHolder.Length = columnsCount;
        headersHolder.Headers = new String[columnsCount];
        for(int i = 0; i < headersHolder.Length; i++)
        {
            String s = get(i);
            headersHolder.Headers[i] = s;
            headersHolder.IndexByName.put(s, new Integer(i));
        }

        if(flag)
            currentRecord--;
        columnsCount = 0;
        return flag;
    }

    public String getHeader(int i)
        throws IOException
    {
        checkClosed();
        if(i > -1 && i < headersHolder.Length)
            return headersHolder.Headers[i];
        else
            return "";
    }

    public boolean isQualified(int i)
        throws IOException
    {
        checkClosed();
        if(i < columnsCount && i > -1)
            return isQualified[i];
        else
            return false;
    }

    private void endColumn()
        throws IOException
    {
        String s = "";
        if(startedColumn)
            if(columnBuffer.Position == 0)
            {
                if(dataBuffer.ColumnStart < dataBuffer.Position)
                {
                    int i = dataBuffer.Position - 1;
                    if(userSettings.TrimWhitespace && !startedWithQualifier)
                        for(; i >= dataBuffer.ColumnStart && (dataBuffer.Buffer[i] == ' ' || dataBuffer.Buffer[i] == '\t'); i--);
                    s = new String(dataBuffer.Buffer, dataBuffer.ColumnStart, (i - dataBuffer.ColumnStart) + 1);
                }
            } else
            {
                updateCurrentValue();
                int j = columnBuffer.Position - 1;
                if(userSettings.TrimWhitespace && !startedWithQualifier)
                    for(; j >= 0 && (columnBuffer.Buffer[j] == ' ' || columnBuffer.Buffer[j] == ' '); j--);
                s = new String(columnBuffer.Buffer, 0, j + 1);
            }
        columnBuffer.Position = 0;
        startedColumn = false;
        if(columnsCount >= 100000 && userSettings.SafetySwitch)
        {
            close();
            throw new IOException("Maximum column count of 100,000 exceeded in record " + NumberFormat.getIntegerInstance().format(currentRecord) + ". Set the SafetySwitch property to false" + " if you're expecting more than 100,000 columns per record to" + " avoid this error.");
        }
        if(columnsCount == values.length)
        {
            int k = values.length * 2;
            String as[] = new String[k];
            System.arraycopy(values, 0, as, 0, values.length);
            values = as;
            boolean aflag[] = new boolean[k];
            System.arraycopy(isQualified, 0, aflag, 0, isQualified.length);
            isQualified = aflag;
        }
        values[columnsCount] = s;
        isQualified[columnsCount] = startedWithQualifier;
        s = "";
        columnsCount++;
    }

    private void appendLetter(char c)
    {
        if(columnBuffer.Position == columnBuffer.Buffer.length)
        {
            int i = columnBuffer.Buffer.length * 2;
            char ac[] = new char[i];
            System.arraycopy(columnBuffer.Buffer, 0, ac, 0, columnBuffer.Position);
            columnBuffer.Buffer = ac;
        }
        columnBuffer.Buffer[columnBuffer.Position++] = c;
        dataBuffer.ColumnStart = dataBuffer.Position + 1;
    }

    private void updateCurrentValue()
    {
        if(startedColumn && dataBuffer.ColumnStart < dataBuffer.Position)
        {
            if(columnBuffer.Buffer.length - columnBuffer.Position < dataBuffer.Position - dataBuffer.ColumnStart)
            {
                int i = columnBuffer.Buffer.length + Math.max(dataBuffer.Position - dataBuffer.ColumnStart, columnBuffer.Buffer.length);
                char ac[] = new char[i];
                System.arraycopy(columnBuffer.Buffer, 0, ac, 0, columnBuffer.Position);
                columnBuffer.Buffer = ac;
            }
            System.arraycopy(dataBuffer.Buffer, dataBuffer.ColumnStart, columnBuffer.Buffer, columnBuffer.Position, dataBuffer.Position - dataBuffer.ColumnStart);
            columnBuffer.Position += dataBuffer.Position - dataBuffer.ColumnStart;
        }
        dataBuffer.ColumnStart = dataBuffer.Position + 1;
    }

    private void endRecord()
        throws IOException
    {
        hasReadNextLine = true;
        currentRecord++;
    }

    public int getIndex(String s)
        throws IOException
    {
        checkClosed();
        Object obj = headersHolder.IndexByName.get(s);
        if(obj != null)
            return ((Integer)obj).intValue();
        else
            return -1;
    }

    public boolean skipRecord()
        throws IOException
    {
        checkClosed();
        boolean flag = false;
        if(hasMoreData)
        {
            flag = readRecord();
            if(flag)
                currentRecord--;
        }
        return flag;
    }

    public boolean skipLine()
        throws IOException
    {
        checkClosed();
        columnsCount = 0;
        boolean flag = false;
        if(hasMoreData)
        {
            boolean flag1 = false;
            do
                if(dataBuffer.Position == dataBuffer.Count)
                {
                    checkDataLength();
                } else
                {
                    flag = true;
                    char c = dataBuffer.Buffer[dataBuffer.Position];
                    if(c == '\r' || c == '\n')
                        flag1 = true;
                    lastLetter = c;
                    if(!flag1)
                        dataBuffer.Position++;
                }
            while(hasMoreData && !flag1);
            columnBuffer.Position = 0;
            dataBuffer.LineStart = dataBuffer.Position + 1;
        }
        rawBuffer.Position = 0;
        rawRecord = "";
        return flag;
    }

    public void close()
    {
        if(!closed)
        {
            close(true);
            closed = true;
        }
    }

    private void close(boolean flag)
    {
        if(!closed)
        {
            if(flag)
            {
                charset = null;
                headersHolder.Headers = null;
                headersHolder.IndexByName = null;
                dataBuffer.Buffer = null;
                columnBuffer.Buffer = null;
                rawBuffer.Buffer = null;
            }
            try
            {
                if(initialized)
                    inputStream.close();
            }
            catch(Exception exception) { }
            inputStream = null;
            closed = true;
        }
    }

    private void checkClosed()
        throws IOException
    {
        if(closed)
            throw new IOException("This instance of the CsvReader class has already been closed.");
        else
            return;
    }

    protected void finalize()
    {
        close(false);
    }

    private static char hexToDec(char c)
    {
        char c1;
        if(c >= 'a')
            c1 = (char)((c - 97) + 10);
        else
        if(c >= 'A')
            c1 = (char)((c - 65) + 10);
        else
            c1 = (char)(c - 48);
        return c1;
    }

    private Reader inputStream;
    private String fileName;
    private UserSettings userSettings;
    private Charset charset;
    private boolean useCustomRecordDelimiter;
    private DataBuffer dataBuffer;
    private ColumnBuffer columnBuffer;
    private RawRecordBuffer rawBuffer;
    private boolean isQualified[];
    private String rawRecord;
    private HeadersHolder headersHolder;
    private boolean startedColumn;
    private boolean startedWithQualifier;
    private boolean hasMoreData;
    private char lastLetter;
    private boolean hasReadNextLine;
    private int columnsCount;
    private long currentRecord;
    private String values[];
    private boolean initialized;
    private boolean closed;
    public static final int ESCAPE_MODE_DOUBLED = 1;
    public static final int ESCAPE_MODE_BACKSLASH = 2;
}


