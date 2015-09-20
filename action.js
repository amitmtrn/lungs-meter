navigator.getUserMedia = navigator.getUserMedia ||
                         navigator.webkitGetUserMedia ||
                         navigator.mozGetUserMedia;

  var ctx = new AudioContext();
  var audio = document.getElementById('myAudio');
  var audioSrc = ctx.createMediaElementSource(audio);
  var analyser = ctx.createAnalyser();
  var analyzeMusic = true;
  var musicMatrix = [];

  // we have to connect the MediaElementSource with the analyser
  audioSrc.connect(analyser);
  // we could configure the analyser: e.g. analyser.fftSize (for further infos read the spec)

  var frequencyData = new Uint8Array(analyser.frequencyBinCount);
  // frequencyBinCount tells you how many values you'll receive from the analyser

  // we're ready to receive some data!
  // loop
  function renderFrame() {
    var musicVector = [];
    if(analyzeMusic === true) {
     requestAnimationFrame(renderFrame);
     console.log(musicMatrix);
    }
     // update data in frequencyData
     analyser.getByteFrequencyData(frequencyData);
     // render frame based on values in frequencyData
     for (var i = 0; i < frequencyData.length; i++ ) {
       if(frequencyData[i] > 180) {
         musicVector.push(frequencyData[i]);
       }
     }

     if(musicVector.length) {
       musicMatrix.push(musicVector);
     }
  }

  if (navigator.getUserMedia) {
     navigator.getUserMedia({ audio: true, video: false },
        function(stream) {
          console.log(stream);
           video.src = window.URL.createObjectURL(stream);
           video.onloadedmetadata = function(e) {
             video.play();
           };
        },
        function(err) {
           console.log("The following error occured: " + err.name);
        }
     );
  } else {
     console.log("getUserMedia not supported");
  }
  audio.play();
  renderFrame();

  setTimeout(function(){
    audio.pause();
    analyzeMusic = false;
  }, 1000);
