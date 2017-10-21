import java.io.File


class MdeGenerator {
	
	String author = 'admin'
	
	String timestamp = new Date().format('yyyy-MM-dd HH:mm:ss')
	
	String sysName = 'ibs'
	
	String basePkgName = "com.inca.saas.${sysName}"
	
	String masterPkgName
	
	String detailPkgName
	
	String masterEntityClassName
	
	String detailEntityClassName
	
	String savePath = "src${File.separator}gen${File.separator}sample"
	
	File saveDir
	
	Class masterEntityClass
	
	Class detailEntityClass
	
	
	//细单中总单实体的成员变量名
	String mappedBy
	//方法名，成员变量首字母大写
	String mappedByFunc
	//总单中细单实体的成员变量名
	String masterMappedBy
	//总单中细单实体的成员方法名
	String masterMappedByFunc
	
	
	String masterFuncPath
	
	String detailFuncPath
	
	def masterTemplates = ['service': 'DocService.java', 'serviceImpl': 'DocServiceImpl.java','convert': 'convert.java']
	
	def detailTemplates= ['service': 'DtlService.java', 'serviceImpl': 'DtlServiceImpl.java']
	
	
}