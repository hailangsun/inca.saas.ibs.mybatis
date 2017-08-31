package com.inca.saas.ibs.util.export;

import java.io.*;
import java.nio.charset.Charset;

public class CsvWriter
{
    private class UserSettings
    {

        public char TextQualifier;
        public boolean UseTextQualifier;
        public char Delimiter;
        public char RecordDelimiter;
        public char Comment;
        public int EscapeMode;
        public boolean ForceQualifier;

        public UserSettings()
        {
            super();
            TextQualifier = '"';
            UseTextQualifier = true;
            Delimiter = ',';
            RecordDelimiter = '\0';
            Comment = '#';
            EscapeMode = 1;
            ForceQualifier = false;
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

        private Letters()
        {
            super();
        }
    }


    public CsvWriter(String s, char c, Charset charset1)
    {
        outputStream = null;
        fileName = null;
        firstColumn = true;
        useCustomRecordDelimiter = false;
        charset = null;
        userSettings = new UserSettings();
        initialized = false;
        closed = false;
        systemRecordDelimiter = System.getProperty("line.separator");
        if(s == null)
            throw new IllegalArgumentException("Parameter fileName can not be null.");
        if(charset1 == null)
        {
            throw new IllegalArgumentException("Parameter charset can not be null.");
        } else
        {
            fileName = s;
            userSettings.Delimiter = c;
            charset = charset1;
            return;
        }
    }

    public CsvWriter(String s)
    {
        this(s, ',', Charset.forName("ISO-8859-1"));
    }

    public CsvWriter(Writer writer, char c)
    {
        outputStream = null;
        fileName = null;
        firstColumn = true;
        useCustomRecordDelimiter = false;
        charset = null;
        userSettings = new UserSettings();
        initialized = false;
        closed = false;
        systemRecordDelimiter = System.getProperty("line.separator");
        if(writer == null)
        {
            throw new IllegalArgumentException("Parameter outputStream can not be null.");
        } else
        {
            outputStream = writer;
            userSettings.Delimiter = c;
            initialized = true;
            return;
        }
    }

    public CsvWriter(OutputStream outputstream, char c, Charset charset1)
    {
        this(((Writer) (new OutputStreamWriter(outputstream, charset1))), c);
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

    public int getEscapeMode()
    {
        return userSettings.EscapeMode;
    }

    public void setEscapeMode(int i)
    {
        userSettings.EscapeMode = i;
    }

    public void setComment(char c)
    {
        userSettings.Comment = c;
    }

    public char getComment()
    {
        return userSettings.Comment;
    }

    public boolean getForceQualifier()
    {
        return userSettings.ForceQualifier;
    }

    public void setForceQualifier(boolean flag)
    {
        userSettings.ForceQualifier = flag;
    }

    public void write(String s, boolean flag)
        throws IOException
    {
        checkClosed();
        checkInit();
        if(s == null)
            s = "";
        if(!firstColumn)
            outputStream.write(userSettings.Delimiter);
        boolean flag1 = userSettings.ForceQualifier;
        if(!flag && s.length() > 0)
            s = s.trim();
        if(!flag1 && userSettings.UseTextQualifier && (s.indexOf(userSettings.TextQualifier) > -1 || s.indexOf(userSettings.Delimiter) > -1 || !useCustomRecordDelimiter && (s.indexOf('\n') > -1 || s.indexOf('\r') > -1) || useCustomRecordDelimiter && s.indexOf(userSettings.RecordDelimiter) > -1 || firstColumn && s.length() > 0 && s.charAt(0) == userSettings.Comment || firstColumn && s.length() == 0))
            flag1 = true;
        if(userSettings.UseTextQualifier && !flag1 && s.length() > 0 && flag)
        {
            char c = s.charAt(0);
            if(c == ' ' || c == '\t')
                flag1 = true;
            if(!flag1 && s.length() > 1)
            {
                char c1 = s.charAt(s.length() - 1);
                if(c1 == ' ' || c1 == '\t')
                    flag1 = true;
            }
        }
        if(flag1)
        {
            outputStream.write(userSettings.TextQualifier);
            if(userSettings.EscapeMode == 2)
            {
                s = replace(s, "\\", "\\\\");
                s = replace(s, "" + userSettings.TextQualifier, "\\" + userSettings.TextQualifier);
            } else
            {
                s = replace(s, "" + userSettings.TextQualifier, "" + userSettings.TextQualifier + userSettings.TextQualifier);
            }
        } else
        if(userSettings.EscapeMode == 2)
        {
            s = replace(s, "\\", "\\\\");
            s = replace(s, "" + userSettings.Delimiter, "\\" + userSettings.Delimiter);
            if(useCustomRecordDelimiter)
            {
                s = replace(s, "" + userSettings.RecordDelimiter, "\\" + userSettings.RecordDelimiter);
            } else
            {
                s = replace(s, "\r", "\\\r");
                s = replace(s, "\n", "\\\n");
            }
            if(firstColumn && s.length() > 0 && s.charAt(0) == userSettings.Comment)
                if(s.length() > 1)
                    s = "\\" + userSettings.Comment + s.substring(1);
                else
                    s = "\\" + userSettings.Comment;
        }
        outputStream.write(s);
        if(flag1)
            outputStream.write(userSettings.TextQualifier);
        firstColumn = false;
    }

    public void write(String s)
        throws IOException
    {
        write(s, false);
    }

    public void writeComment(String s)
        throws IOException
    {
        checkClosed();
        checkInit();
        outputStream.write(userSettings.Comment);
        outputStream.write(s);
        if(useCustomRecordDelimiter)
            outputStream.write(userSettings.RecordDelimiter);
        else
            outputStream.write(systemRecordDelimiter);
        firstColumn = true;
    }

    public void writeRecord(String as[], boolean flag)
        throws IOException
    {
        if(as != null && as.length > 0)
        {
            for(int i = 0; i < as.length; i++)
                write(as[i], flag);

            endRecord();
        }
    }

    public void writeRecord(String as[])
        throws IOException
    {
        writeRecord(as, false);
    }

    public void endRecord()
        throws IOException
    {
        checkClosed();
        checkInit();
        if(useCustomRecordDelimiter)
            outputStream.write(userSettings.RecordDelimiter);
        else
            outputStream.write(systemRecordDelimiter);
        firstColumn = true;
    }

    private void checkInit()
        throws IOException
    {
        if(!initialized)
        {
            if(fileName != null)
                outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), charset));
            initialized = true;
        }
    }

    public void flush()
        throws IOException
    {
        outputStream.flush();
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
                charset = null;
            try
            {
                if(initialized)
                    outputStream.close();
            }
            catch(Exception exception) { }
            outputStream = null;
            closed = true;
        }
    }

    private void checkClosed()
        throws IOException
    {
        if(closed)
            throw new IOException("This instance of the CsvWriter class has already been closed.");
        else
            return;
    }

    protected void finalize()
    {
        close(false);
    }

    public static String replace(String s, String s1, String s2)
    {
        int i = s1.length();
        int j = s.indexOf(s1);
        if(j > -1)
        {
            StringBuffer stringbuffer = new StringBuffer();
            int k = 0;
            for(; j != -1; j = s.indexOf(s1, k))
            {
                stringbuffer.append(s.substring(k, j));
                stringbuffer.append(s2);
                k = j + i;
            }

            stringbuffer.append(s.substring(k));
            return stringbuffer.toString();
        } else
        {
            return s;
        }
    }

    private Writer outputStream;
    private String fileName;
    private boolean firstColumn;
    private boolean useCustomRecordDelimiter;
    private Charset charset;
    private UserSettings userSettings;
    private boolean initialized;
    private boolean closed;
    private String systemRecordDelimiter;
    public static final int ESCAPE_MODE_DOUBLED = 1;
    public static final int ESCAPE_MODE_BACKSLASH = 2;
}


