//整单终止
function stopDoc(url,funcPath,docGrid){
	var docRows = docGrid.getSelecteds();
	if (docRows.length > 0) {
	    var docIds = [];
        for (var i = 0, l = docRows.length; i < l; i++) {
            var r = docRows[i];
            docIds.push(r.id);
        }
        var docDtlFlag = "doc";
        var docIdJoin = docIds.join(',');
        url += "?docDtlFlag="+docDtlFlag+"&ids="+docIdJoin+"&funPath="+funcPath;
        var mid = 'stopReason';
		var dialog = $('#' + mid);
		if (dialog.length == 0) {
			dialog = $('<div class="modal fade" role="dialog" id="' + mid + '"/>');
			dialog.appendTo($(document.body));
		}
		dialog.load(url, function() {
			dialog.modal();
		});
        
	}else{
		mini.alert("请选择一行数据！");
	}
}

//细单终止
function stopDtl(url,funcPath,docId,dtlGrid,id){
	var dtlRows = dtlGrid.getSelecteds();
	if (dtlRows.length > 0) {
	    var dtlIds = [];
        for (var i = 0, l = dtlRows.length; i < l; i++) {
            var r = dtlRows[i];
            dtlIds.push(r.id);
        }
        var docDtlFlag = "dtl";
        var dtlIdJoin = dtlIds.join(',');
        url += "?docDtlFlag="+docDtlFlag+"&ids="+dtlIdJoin+"&docId="+docId+"&funPath="+funcPath;
        var mid = 'stopReason';
		var dialog = $('#' + mid);
		if (dialog.length == 0) {
			dialog = $('<div class="modal fade" role="dialog" id="' + mid + '"/>');
			dialog.appendTo($(document.body));
		}
		dialog.load(url, function() {
			dialog.modal();
		});
        
	}else{
		mini.alert("请选择一行数据！");
	}
	
	
	var docDtlFlag = "dtl";
	url += "?docDtlFlag=" + docDtlFlag + "&docId=" + docId + "&funPath=" + funcPath;
	var tools    = TableTools.fnGetInstance(datatable);
    var selDatas = tools.fnGetSelectedData();
    if (selDatas.length < 1) {
    	bootbox.alert("请至少选择一行数据");
    	return false;
    } else {
		var mid = 'stopReason';
		var dialog = $('#' + mid);
		if (dialog.length == 0) {
			dialog = $('<div class="modal fade" role="dialog" id="' + mid + '"/>');
			dialog.appendTo($(document.body));
		}
		dialog.load(url, function() {
			dialog.modal();
		});
    }
}