<template>
  <div class="owl-item active" style="width: 70.143px; margin-right: 6px;">

    <div data-index="6">
      <a href="javascript:changemImg('6');" class=""><img
        src="http://img.sivillage.com/files/product/01/P0000/26/58/92/01P0000265892_2.jpg?RS=70&amp;SP=1"
        data-index="6"></a>
    </div>
    <ul class="extra-func">
      <li><a href="javascript:#;"  @click="showExtra()" class="mov-btn active">
        <img src="http://img.sivillage.com/files/product/01/P0000/26/58/92/01P0000265892_72.jpg" onerror="this.src='/images/noImg/noImg_72.jpg'; this.className='no_img'" alt="클래식 오버핏 트렌치 코트" class="">
        <i class="icon"></i>
      </a></li>
    </ul>
    <iframe width="860" height="860" src="http://v.kr.kollus.com/pAwtK3fm?pf=ssgsiv-pc2-high&amp;autoplay" frameborder="0" title="동영상" allowfullscreen="" webkitallowfullscreen="" mozallowfullscreen=""></iframe>
  </div>

</template>
<script>
  export default {
        name: "si_vue"
        ,methods: {
            showExtra(){
              alert("ss")
            }
        }
    }



</script>

<style scoped>

</style>
