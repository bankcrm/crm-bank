<nav class="navbar navbar-inverse" style="margin-bottom:0; border-radius:0;">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active" ><a href="#">Home</a></li>
        <li><a href="#" >About</a></li>
   
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.request.contextPath}/mybank/auth"><span class="glyphicon glyphicon-user"></span> Your Account</a></li>
        <li><a href="${pageContext.request.contextPath}/mybank/logout">logout</a></li>
      </ul>
    </div>
  </div>
</nav>
