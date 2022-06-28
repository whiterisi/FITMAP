<template>
  <div class ="container">
    <div class="grid">
          <div v-for ="(video, index) in likeVideos" :key="index">
            <figure class="effect-zoe">
              <iframe :src='`https://www.youtube.com/embed/${video.videoId}`'></iframe>
              <figcaption>
                <div  id="title">{{video.title}}</div>
                 <button id="likebtn" @click="deleteLike(`${video.videoId}`)">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
                      <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                      </svg>
                  </button>
                  <button id="detailbtn" @click="addlike(`${video.videoId}`)">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-aspect-ratio" viewBox="0 0 16 16">
                    <path d="M0 3.5A1.5 1.5 0 0 1 1.5 2h13A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 12.5v-9zM1.5 3a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-13z"/>
                    <path d="M2 4.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1H3v2.5a.5.5 0 0 1-1 0v-3zm12 7a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1 0-1H13V8.5a.5.5 0 0 1 1 0v3z"/>
                    </svg>
                  </button>
                <p class="description"></p>
              </figcaption>
            </figure>
            </div>
      </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  computed:{
    ...mapState([
      'likeVideos'
    ])
  },
  created(){
    this.$store.dispatch('getLikeList')

  },
  methods:{
    deleteLike(videoID){
      const likeList={
        userId: this.$store.getters.GET_USERID,
        videoId: videoID,
      }
      console.log(videoID)
      this.$store.dispatch('deleteLike', likeList)
    }
  }
}
</script>

<style scoped>

#likebtn{
	margin:2px;
	background-color: #db5224e5;
	border-radius: 50%;
}
#likebtn:hover {
  background: rgba(14, 12, 67, 0.866); 
  color: white;
}
#title{
	overflow: hidden;
}

#detailbtn{
	margin:2px;
	background-color: #3e89ecb7;
	border-radius: 50%;
}
#detailbtn:hover {
  background: rgb(71, 62, 225); 
  color: white;
}
  .container{
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
  }
  span.highlight {
    color: rgba(220, 51, 51, 0.865);
  }

  #recommend_comment{
    font-size : 30px;
  }
  #explanation{
    font-size : 20px;
  }


.grid {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;

	position: relative;
	margin: 0 auto;
	padding: 1em 0 4em;
	max-width: 1000px;
	list-style: none;
	text-align: center;
}

/* Common style */
.grid figure {
	position: relative;
	float: left;
	overflow: hidden;
	margin: 5px 1%;
	min-width: 320px;
	/* max-width: 500px; */
	max-height: 600px;
	width: 48%;
	background: #f6f8f8;
	text-align: center;
	cursor: pointer;
}

.grid figure img {
	position: relative;
	display: block;
	min-height: 100%;
	max-width: 100%;
	opacity: 0.8;
}

.grid figure figcaption {
	padding: 2em;
	color: rgba(134, 211, 237, 0.406);
	text-transform: uppercase;
	font-size: 1.25em;
	-webkit-backface-visibility: hidden;
	backface-visibility: hidden;
}

.grid figure figcaption::before,
.grid figure figcaption::after {
	pointer-events: none;
}

.grid figure figcaption,
.grid figure figcaption > a {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

/* Anchor will cover the whole item by default */
/* For some effects it will show as a button */
.grid figure figcaption > a {
	z-index: 1000;
	text-indent: 200%;
	white-space: nowrap;
	font-size: 0;
	opacity: 0;
}

.grid figure p {
	margin: 0;
}

.grid figure p {
	letter-spacing: 1px;
	font-size: 68.5%;
}

figure.effect-zoe figcaption {
	top: auto;
	bottom: 0;
	padding: 1em;
	height: 8em;
	background: rgba(18, 18, 18, 0.686);
	color: #dee9ee;
	-webkit-transition: -webkit-transform 0.35s;
	transition: transform 0.35s;
	-webkit-transform: translate3d(0,100%,0);
	transform: translate3d(0,100%,0);
}

figure.effect-zoe h2 {
	float: left;
}

figure.effect-zoe p.icon-links button {
	float: right;
	color: #3c4a50;
	font-size: 1.4em;
}

figure.effect-zoe:hover p.icon-links button:hover,
figure.effect-zoe:hover p.icon-links button:focus {
	color: #252d31;
}

figure.effect-zoe p.description {
	position: absolute;
	bottom: 2em;
	padding: 2em;
	color: #fff;
	text-transform: none;
	font-size: 90%;
	opacity: 0;
	-webkit-transition: opacity 0.35s;
	transition: opacity 0.35s;
	-webkit-backface-visibility: hidden; /* Fix for Chrome 37.0.2062.120 (Mac) */
}

figure.effect-zoe h2,
figure.effect-zoe p.icon-links a {
	-webkit-transition: -webkit-transform 0.35s;
	transition: transform 0.35s;
	-webkit-transform: translate3d(0,200%,0);
	transform: translate3d(0,200%,0);
}


figure.effect-zoe h2 {
	display: inline-block;
}

figure.effect-zoe:hover p.description {

	opacity: 1;
}

figure.effect-zoe:hover figcaption{
	-webkit-transform: translate3d(0,0,0);
	transform: translate3d(0,0,0);
}

figure.effect-zoe:hover h2 {
	-webkit-transition-delay: 0.05s;
	transition-delay: 0.05s;
}

figure.effect-zoe:hover p.icon-links a:nth-child(3) {
	-webkit-transition-delay: 0.1s;
	transition-delay: 0.1s;
}

figure.effect-zoe:hover p.icon-links a:nth-child(2) {
	-webkit-transition-delay: 0.15s;
	transition-delay: 0.15s;
}

figure.effect-zoe:hover p.icon-links a:first-child {
	-webkit-transition-delay: 0.2s;
	transition-delay: 0.2s;
}
</style>>
