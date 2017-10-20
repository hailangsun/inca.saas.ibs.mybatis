package com.inca.saas.ibs.codegen;

import java.lang.reflect.Field

import com.inca.saas.ibs.entity.IBSBaseEntity;
import com.inca.saas.metadata.ColumnReader
import com.inca.saas.metadata.ConstraintReader
import com.inca.saas.metadata.FuncReader;
import com.inca.saas.metadata.IDReader
import com.inca.saas.metadata.ManyToManyReader;
import com.inca.saas.metadata.ManyToOneReader;
import com.inca.saas.metadata.NoEditableReader
import com.inca.saas.metadata.OneToManyReader;
import com.inca.saas.metadata.OneToOneReader
import com.inca.saas.metadata.OptionReader;
import com.inca.saas.metadata.QueryableReader
import com.inca.saas.metadata.TitleReader
import com.inca.saas.metadata.TransientReader


/**
 * @author liubin
 *
 */
class EntityMetadata<T> {

	String className

	String pkgName

	String detailPkgname

	String subsysName

	String funcName

	String title

	String funcCode

	Class<T> entityClass
	//是否扫描父类成员
	boolean scanSuper=true
	//父类字段是否允许生成查询条件
	boolean genQueryBySuper=false

	boolean isInstanceOfIbsEntity=false

	boolean hasCreateUserId = false
	
	//扫描到baseEntityClassName后不继续递归
	String baseEntityClassName='com.inca.saas.BaseEntity'

	List<FieldMetadata> fieldMetadataList = []

	List<FieldMetadata> noIdfieldMetadataList = []

	List<FieldMetadata> idfieldMetadataList = []

	List<FieldMetadata> noEditableFieldMetadataList = []

	List<FieldMetadata> editableFieldMetadataList = []

	//父类不可编辑字段(dept)
	List<FieldMetadata> superNoEditableFieldMetadataList = []
	//父类可编辑字段（memo）
	List<FieldMetadata> superEditableFieldMetadataList = []
	//父类隐藏字段(Id字段)
	List<FieldMetadata> hiddenFieldMetadataList=[]

	List<FieldMetadata> optionFieldMetadataList = []

	List<String> excludeFields = ['serialVersionUID']

	List<FieldMetadata> keywordFieldMetadataList=[]



	Map<String, String> queryLabelMap=new LinkedHashMap<>();

	String idName;

	Class idType;

	EntityMetadata(Class<T> entityClass,boolean scanSuper=true) {
		super()

		this.entityClass = entityClass
		this.scanSuper = scanSuper

	}
	Class getFieldType(String name) {
		FieldMetadata fmd = fieldMetadataList.find { it.name == name }

		fmd?.type
	}

	String getFiledCapitalize(String name) {
		name.capitalize()
	}

	boolean isNumberField(String name) {
		FieldMetadata fmd = fieldMetadataList.find { it.name == name }

		if (null == fmd) {
			false
		} else {
			def type = fmd.type
			type == Integer || type == int || type == Byte || type == byte || type == Short || type == short || type == Long || type == long || type == Float || type == float || type == Double || type == double || type == BigDecimal || type == BigInteger
		}
	}

	boolean isBooleanField(String name) {
		FieldMetadata fmd = fieldMetadataList.find { it.name == name }

		if (null == fmd) {
			false
		} else {
			def type = fmd.type
			type == Boolean || type == boolean
		}
	}

	boolean isDateField(String name) {
		FieldMetadata fmd = fieldMetadataList.find { it.name == name }

		if (null == fmd) {
			false
		} else {
			def type = fmd.type
			type == Date
		}
	}
	String getKeywordPlaceholder(){
		StringBuilder holderBuilder=new StringBuilder();
		def count=0;
		for(field in keywordFieldMetadataList){
			String keyword=field.queryLabel
			if(count!=0)holderBuilder.append("/")
			if(keyword!=null&&keyword.length()>0){
				holderBuilder.append(keyword)
				count++;
			}
		}
		return holderBuilder.toString();
	}
	String getHtmlInputType(String name) {
		String type = "text"
		if (isBooleanField(name)) {
			type = "checkbox"
		} else if (isNumberField(name)) {
			type = "number"
		} else if (isDateField(name)) {
			type = "date"
		}

		type
	}
	boolean isId(String name){
		boolean hidden=false;
		hiddenFieldMetadataList.forEach(){FieldMetadata field->
			if(field.name==name){
				hidden=true
			}
		}
		return hidden
	}
	/**
	 * 扫描{clazz}中包含的成员变量
	 * @param fieldMap
	 * @param clazz
	 * @return
	 */
	def scanFields(Map<Class,Field[]> fieldMap,Class clazz){
		if(clazz.getName()!=baseEntityClassName){
			println '==============scanClass========='+clazz.name
			Field[] fs=clazz.declaredFields
			for(field in fs){
				println field.getName()
			}
			fieldMap.put(clazz,fs)
			if(clazz.superclass!=null){
				scanFields(fieldMap,clazz.superclass)
			}
		}
		fieldMap
	}
	def static boolean  isIntanceOfIBS(Class clazz){
		return (clazz.newInstance() instanceof IBSBaseEntity)
	}
	static void main(String... args) {
		println	isIntanceOfIBS (com.inca.saas.ibs.entity.pub.Area.class)
	}
	def parse() {
		this.className = entityClass.simpleName
		println "className: ${className}"

		this.pkgName = entityClass.package.name
		println "pkgName: ${pkgName}"

		String[] ss = pkgName.split('\\.')
		this.subsysName = ss[ss.length - 1]
		println "subsysName: ${subsysName}"

		this.funcName = this.className.substring(0, 1).toLowerCase() + this.className.substring(1)
		println "funcName: ${funcName}"

		TitleReader<T> titleReader = new TitleReader<T>(entityClass)
		this.title = titleReader.title
		println "entity title: ${title}"

		FuncReader<T> funcReader = new FuncReader<T>(entityClass)
		this.funcCode = funcReader.code

		TransientReader transientReader
		println "entity funcCode: ${funcCode}"
		//		if (!funcCode) {
		//			funcCode = '_FUNCCODE_'
		//		} else {
		//			funcCode = funcCode.toLowerCase()
		//		}
		IDReader<T> idReader = new IDReader<T>(entityClass);
		this.idName = idReader.getName();
		if(idName!=null)
			println "entity idname: ${idName}"
		this.idType = idReader.getType();
		if(idType!=null)
			println "entity idtype: ${idType}"



		isInstanceOfIbsEntity=(isIntanceOfIBS(entityClass))&&scanSuper

		println "isInstanceOfIbsEntity:${isInstanceOfIbsEntity}"

		Field[] fields = entityClass.declaredFields
		Map<Class,Field[]> fieldMap=new LinkedHashMap<Class,Field[]>();
		if(scanSuper){
			scanFields fieldMap,entityClass
		}else{
			fieldMap.put entityClass,fields
		}
		fieldMap.each{entry ->
			Class clazz = entry.key
			QueryableReader<T> queryableReader = new QueryableReader<T>(clazz)

			NoEditableReader<T> noEditableReader = new NoEditableReader<T>(clazz)

			ColumnReader<T> columnReader = new ColumnReader<T>(clazz)

			ConstraintReader<T> constraintReader = new ConstraintReader<T>(clazz)

			OptionReader<T> optionReader = new OptionReader<T>(clazz)

			OneToOneReader<T> oneToOneReader = new OneToOneReader<T>(clazz)
			OneToManyReader<T> oneToManyReader = new OneToManyReader<T>(clazz)
			ManyToOneReader<T> manyToOneReader = new ManyToOneReader<T>(clazz)
			ManyToManyReader<T> manyToManyReader = new ManyToManyReader<T>(clazz)

			titleReader = new TitleReader<T>(clazz)
			transientReader=new TransientReader(clazz);
			if(idName==null||idType==null){
				idReader = new IDReader<T>(clazz);
				this.idName = idReader.getName();
				if(idName!=null)
					println "entity idname: ${idName}"
				this.idType = idReader.getType();
				if(idType!=null)
					println "entity idtype: ${idType}"
			}

			Map<String,String> map=queryableReader.queryableFields
			if(entityClass==clazz||(entityClass!=clazz&&genQueryBySuper)){

				if(map!=null){
					queryLabelMap.putAll(map)
				}
			}
			fields= entry.value
			fields.each {Field field ->
				if (!excludeFields.contains(field.name)) {
					println "field: ${field}"



					if(!transientReader.isTransient(field.name)){


						if (!(oneToOneReader.isOneToOne(field.name) || oneToManyReader.isOneToMany(field.name) || manyToOneReader.isManyToOne(field.name) || manyToManyReader.isManyToMany(field.name))) {
							FieldMetadata fmd = new FieldMetadata(field)
							fmd.titleReader = titleReader
							fmd.queryableReader =queryableReader
							fmd.noEditableReader =noEditableReader
							fmd.columnReader =columnReader
							fmd.constraintReader =constraintReader
							fmd.optionReader =optionReader
							fmd.parse()
							//						fmd.isId = (fmd.name == this.idName||fmd.isId)
							fieldMetadataList << fmd
							if(fmd.isKeyword){
								this.keywordFieldMetadataList<<fmd
							}
							//子类
							if(entityClass==clazz){
								//主键判断
								if (fmd.name == this.idName) {
									this.idfieldMetadataList << fmd
								} else {
									this.noIdfieldMetadataList << fmd
									if (fmd.editable) {
										this.editableFieldMetadataList << fmd
									} else {
										this.noEditableFieldMetadataList << fmd
									}
									if (fmd.optionName) {
										this.optionFieldMetadataList << fmd
									}
								}
							}else{
								//父类不存在主键ID字段，直接进去
								if(fmd.isId){
									//Id字段隐藏
									this.hiddenFieldMetadataList << fmd
									if (fmd.name.equals('createUserId')) {
										hasCreateUserId = true
									}
								}else{
									this.noIdfieldMetadataList << fmd
									if (fmd.editable) {
										this.superEditableFieldMetadataList << fmd
									} else {
										this.superNoEditableFieldMetadataList << fmd
									}
									if (fmd.optionName) {
										this.optionFieldMetadataList << fmd
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
