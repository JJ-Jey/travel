function pay(btnTag){
	var amount=$(btnTag).siblings(".amount").val();
	var name=$(btnTag).siblings(".name").val();
	
	var IMP = window.IMP; // 생략 가능
	IMP.init("imp45644180");
	
	IMP.request_pay({
      pg: "kcp",
      pay_method: "card",
      merchant_uid: "ORD"+new Date().getTime(),   // 주문번호
      name: name,
      amount: amount,                         // 숫자 타입
      buyer_email: "qwer@gmail.com",
      buyer_name: "구매자",
      buyer_tel: "010-1234-1234",
      buyer_addr: "서울특별시 노원구 상계동",
      buyer_postcode: "01234"
    }, function (rsp) { // callback
      //rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
      var msg="";
      if(rsp.success){
		  msg="결제완료";
	  }else{
		  msg="결제실패";
	  }
	  alert(msg);
    });
 }