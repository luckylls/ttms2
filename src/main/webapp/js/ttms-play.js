$(function(){
	//绑定查询监听
	$("#btn-select").click(function(){
		$.ajax({
			type:"get",
			url:"/play/list",
			async:true,
			dataType: "json",
			success: function(data){
				createShowTable(data.data);
			}
		})
		function createShowTable(data){
			var tablestr = "<table class='tab-list' width='99%'>"
			tablestr = tablestr
			+"<tr class='tr-header'>"
			+"<td width='2%'>剧目ID</td>"
			+"<td width='2%'>剧目类型ID</td>"
			+"<td width='2%'>剧目语言ID</td>"
			+"<td width='10%'>剧目名称</td>"
			+"<td width='10%'>剧目简介</td>"
			+"<td width='10%'>剧目海报</td>"
			+"<td width='2%'>剧目时长</td>"
			+"<td width='2%'>剧目票价</td>"
			+"<td width='2%'>剧目状态</td>"
			+"<td width='2%'>剧目票房</td>"
			+"</tr>"
			var len = data.length
			for(var i = 0;i < len; i++){
				tablestr = tablestr + "<tr>"
				+"<td>"+data[i].playId+"</td>"
				+"<td>"+data[i].playTypeId+"</td>"
				+"<td>"+data[i].playLangId+"</td>"
				+"<td>"+data[i].playName+"</td>"
				+"<td>"+data[i].playIntroduction+"</td>"
				+"<td>"+data[i].playImage+"</td>"
				+"<td>"+data[i].playLength+"</td>"
				+"<td>"+data[i].playTicketPrice+"</td>"
				+"<td>"+data[i].playStatus+"</td>"
				+"<td>"+data[i].playBookingOffice+"</td>"
				+"</tr>"
			}
			if(len==0){
				tablestr += "<tr style='text-align:center'>'+<td >"+暂无记录+"</td>+'</tr>"
			}
			tablestr += "</table>"
			$('#center > .wrap').html(tablestr)
		}
			
	});
	
    
})