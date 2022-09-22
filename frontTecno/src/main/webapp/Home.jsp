<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/home.css">
<title>Home</title>
</head>
<body>
	<section id="container-slider">	
            <a href="javascript: fntExecuteSlide('prev');" class="arrowPrev"><i class="fas fa-chevron-circle-left"></i></a>
            <a href="javascript: fntExecuteSlide('next');" class="arrowNext"><i class="fas fa-chevron-circle-right"></i></a>
            <ul class="listslider">
              <li><a itlist="itList_0" href="#" class="item-select-slid"></a></li>
              <li><a itlist="itList_1" href="#"></a></li>
              <li><a itlist="itList_2" href="#"></a></li>
            </ul>
            <ul id="slider">
              <li style="background-image: url('img/sedebogota.jpg'); z-index:0; opacity: 1;">
                <div class="content_slider" >
                	<div>
                    	<h2>Bogota</h2>                    
              		</div>
                </div>
              </li>
              <li style="background-image: url('img/sedecali.jpg'); ">
                <div class="content_slider" >
                  	<div>
                    	<h2>Cali</h2>                   
              		</div>
                </div>
              </li>
              <li style="background-image: url('img/sedemedellin.jpg'); ">
                <div class="content_slider" >
                 	<div>
                    	<h2>Medellin</h2>                   
              		</div>
                </div>
              </li>
           </ul>
         </section>
        <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
        <script defer src="js/home.js"></script>
</body>
</html>