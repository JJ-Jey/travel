/**
 * 
 */
var items;
var target_idx=0;
var speed;
var delay=3000;
var timmer;

var bullets;

$(function(){
	items=$("#visual .item");
	bullets=$("#visual .bullet");
	move(0); //초기화
	auto();
	
	//$("#visual .wrap").hover(stop, auto); //auto-wrap 월활한 작동 위해 주석 처리
	$(".btn-wrap .btn").hover(stop, auto); //버튼 위에 올렸을 때 정지
	
	$(".bullet-wrap .bullet").click(bulletClicked); //단추 버튼 눌러서 바로 이동
	
	$(".auto-wrap button span").click(playStopClicked); //시작-정지 버튼
});

function playStopClicked(){
	$(this).hide();
	$(this).siblings().show();
	var playOrStrop=$(this).index(); //0:play 1:stop
	if(playOrStrop==0)auto();
	if(playOrStrop==1)stop();
}

document.addEventListener("visibilitychange", function(){
	if(document.visibilityState=="hedden"){
		stop();
	} else if(document.visibilityState=="visible"){
		auto();
	}
})

function bulletClicked(){
	var bi=$(this).index();
	target_idx=bi;
	move(0);
}

function auto(){
	stop();
	timmer=setTimeout(start, delay);
}

function stop(){
	clearTimeout(timmer);
}

function start(){
	move(1);
	auto();
}

function move(dir){
	target_idx=(target_idx+dir) % items.length;
	var prev=(target_idx-1) % items.length;
	var next=(target_idx+1) % items.length;
	items.removeClass("target prev next");
	items.eq(target_idx).addClass("target");
	items.eq(prev).addClass("prev");
	items.eq(next).addClass("next");
	
	bullets.removeClass("target");
	bullets.eq(target_idx).addClass("target");
	
	$(".txt .target").text($(".item.target").index()+1);
	$(".txt .tot").text(items.length);
}