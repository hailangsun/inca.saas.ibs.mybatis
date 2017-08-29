Date.prototype.format = function(fmt) {
	if (!fmt) {
		fmt = "yyyy-MM-dd";
	}
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

Date.prototype.compareTo = function(starttime, endtime) {
	var start = new Date(starttime.replace("-", "/").replace("-", "/"));
	var end = new Date(endtime.replace("-", "/").replace("-", "/"));
	if (start > end) {
		return 1;
	} else if (start = end) {
		return 0;
	} else {
		return -1;
	}
}