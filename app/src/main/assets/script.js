var media_events = new Array();
media_events["loadstart"] = 0;
media_events["progress"] = 0;
media_events["suspend"] = 0;
media_events["abort"] = 0;
media_events["error"] = 0;
media_events["emptied"] = 0;
media_events["stalled"] = 0;
media_events["loadedmetadata"] = 0;
media_events["loadeddata"] = 0;
media_events["canplay"] = 0;
media_events["canplaythrough"] = 0;
media_events["playing"] = 0;
media_events["waiting"] = 0;
media_events["seeking"] = 0;
media_events["seeked"] = 0;
media_events["ended"] = 0;
media_events["durationchange"] = 0;
media_events["timeupdate"] = 0;
media_events["play"] = 0;
media_events["pause"] = 0;
media_events["ratechange"] = 0;
media_events["resize"] = 0;
media_events["volumechange"] = 0;

var media_properties = ["error", "src", "srcObject", "currentSrc", "crossOrigin", "networkState", "preload",
	"buffered", "readyState", "seeking", "currentTime", "duration",
	"paused", "defaultPlaybackRate", "playbackRate", "played", "seekable", "ended", "autoplay", "loop", "controls",
	"volume","muted", "defaultMuted", "audioTracks", "videoTracks", "textTracks", "width", "height", "videoWidth",
	"videoHeight", "poster"];

var media_properties_elts = null;


var init_intval=0;
var inited=false;

function init() {
	console.log("init");

    init_intval=setInterval(getVideo,1000);

}

function config_video(){

    console.log("config_video");

    var videoElem=document.getElementsByTagName("video");

    if(videoElem.length>0){
        document._video= document.getElementsByTagName("video")[0];
}
else{// not found video tag, find iframe.video
    var IMGmatches = [], IMGelems = document.getElementsByTagName("video"),
            iframes = document.getElementsByTagName('iframe'), l = IMGelems.length,
            m = iframes.length, i, j;

        for( i=0; i<l; i++) IMGmatches[i] = IMGelems[i];
        for( j=0; j<m; j++) {
            IMGelems = iframes[j].contentDocument.getElementsByTagName("video");
            l = IMGelems.length;
            for( i=0; i<l; i++) IMGmatches.push(IMGelems[i]);
        }

    //	document._video= document.getElementsByTagName("video")[0];

        document._video=IMGmatches[0];

        iframe0=iframes[0];
        if(iframe0 !='undefined'){
            iframe0.webkitRequestFullscreen();
        }

        console.log("iframe="+iframe0);
    }
	media_properties_elts = {};

	init_events(document._video, media_events);
	init_properties("properties", media_properties, media_properties_elts);

	// properties are updated even if no event was triggered
	setInterval(update_properties, 1000);

	clearInterval(init_intval);

	inited=true;

}


document.addEventListener("DOMContentLoaded", init, false);

function init_events(id, arrayEventDef) {
    console.log("init_events");
	for (key in arrayEventDef) {
		id.addEventListener(key, capture, false);
	}
}

function init_properties(id, arrayPropDef, arrayProp) {
    console.log("init_properties");
	var i = 0;
	do {
		var r;
		r = eval("document._video." + arrayPropDef[i]);
		arrayProp[arrayPropDef[i]] = r;

		i++;
	} while (i < arrayPropDef.length);

}

function capture(event) {
	media_events[event.type]++;

	videoObj.processEvents(event.type+'');
}

function update_properties() {
//    console.log("update_properties:"+videoObj);

	var i = 0;
	for (key in media_properties) {
		var val = eval("document._video." + media_properties[key]);
		media_properties_elts[media_properties[key]] = val;
		i++;
	}

	var properties = JSON.stringify(media_properties_elts);

//    console.log("update_properties:"+properties+","+media_properties_elts);

	videoObj.processProperties(properties+'');

}

function getVideo() {

//    console.log("getvideo:"+document._video);
    if(typeof(document._video) == 'undefined' || !inited){
        config_video();
    }

	return document._video;
}