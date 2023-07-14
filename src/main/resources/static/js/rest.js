/**
 * 
 */
$(function(){
				$("form").submit(function(event){
					event.preventDefault();
				});
				
				$("#btn-search").click(function(){
					btnSearchClicked(1);
				});
				restBoardList(1)
				
			});
			
			function btnSearchClicked(page){
				
				var data=$("#form-search").serialize()+"&page="+page;
				$.ajax({
					url:"/rest-boards/search",
					type: "PATCH",
					data:data,
					success:function(result){
						$("#board-list").html(result);
					}
				});
			}
			
			function restBoardList(page){
				console.log('비동기1');
				
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				$(document).ajaxSend(function(e, xhr, options) {
					xhr.setRequestHeader(header, token);
				});
				
				console.log('비동기2');
				
				$.ajax({
					url:"/rest-boards",
					type: "PATCH",
					data:{page:page},
					success:function(result){
						console.log('비동기 성공');
						$("#board-list").html(result);
					}
				});
			}