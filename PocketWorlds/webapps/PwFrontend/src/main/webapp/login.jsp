<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Login Page</title>
        <style>
            .errorblock {
                color: #ff0000;
                background-color: #ffEEEE;
                border: 3px solid #ff0000;
                padding: 8px;
                margin: 16px;
            }
            body{
                background-color: #BAD376;
                font-size: 12px;
                font-family: Verdana, Arial, Helvetica, Sans-Serif;
                color: #5d717e;
                text-align: center
            }
            a{
                color: #cccccc;
            }
            root { 
                display: block;
            }

            .header{
                width: 100%;
                background-color: #5D717E;
                display: table;
                padding: 10px 5px;
            }

            .user{
                width: 33%;
                display: inline-block;
                color:#ffffff;
            }
            .title{
                width: 33%;
                display: inline-block;
                color:#ffffff
            }
            .title span{
                display: block;
                text-align: center;
                width: 100%;
                font-size: 2em;
            }
            .logout{
                width: 33%;
                display: inline-block;
                color:#ffffff
            }
            .logout a{
                float: right;
            }

            .fullpage{
                width: 650px;
                margin: 0 auto 2px auto;
                text-align: left;
                background-color: white;
                border-top: solid #BAD376 6px;
                display: table;
            }


            a:link{
                color:#ffffff;
            }

            a:visited{
                color:#99ffff;
            }

            /* Menu*/
            .leftMenu{
                float:left;
                position:relative;
                width: 200px;

            }
            #navigation {
                width: 200px;
                list-style: none;
                margin: 0;
                padding: 0;
            }
            #navigation ul{
                list-style-type: none;
                margin-left: -30px;
            }
            #navigation li {
                background:#5D717E ;
                color: #fff ;
                border: 1px solid #600 ;
                margin-bottom: 1px ;
            }
            #navigation li a {
                display: block ;
                background:#5D717E ;
                color: #fff ;
                line-height: 1em ;
                text-align: center ;
                text-decoration: none ;
                padding: 4px 0 ;
            }
            #navigation li a:hover, #navigation li a:focus, #navigation li a:active {
                background: #cccccc ;
                text-decoration: underline ;
            }

            .content{
                padding: 10px;
                float: left;
                position: relative;
                width: 740px;
                display: table;
            }

            .spacer{clear:both; height:1px;}

            .myform{
                margin:0 auto;
                width:600px;
                padding:14px;
            }

            /* ----------- stylized ----------- */
            #stylized{
                border:solid 2px #5D717E;
                background:#EBF4D1;
            }
            #stylized h1 {
                font-size:14px;
                font-weight:bold;
                margin-bottom:8px;
            }
            #stylized p{
                font-size:11px;
                color:#666666;
                margin-bottom:20px;
                border-bottom:solid 1px #b7ddf2;
                padding-bottom:10px;
            }
            #stylized td{
                display:block;
                font-weight:bold;
                text-align:right;
                width:240px;
                float:left;
            }
            #stylized img{
                display:block;
                text-align:right;
                width:240px;
                float:left;
            }
            #stylized .small{
                color:#666666;
                display:block;
                font-size:11px;
                font-weight:normal;
                text-align:right;
                width:240px;
            }
            #stylized input{
                float:left;
                font-size:12px;
                padding:4px 2px;
                border:solid 1px #aacfe4;
                width:300px;
                margin:2px 0 20px 10px;
            }
            #stylized button{
                clear:both;
                margin-left:150px;
                width:125px;
                height:31px;
                background:#666666 url(img/button.png) no-repeat;
                text-align:center;
                line-height:31px;
                color:#FFFFFF;
                font-size:11px;
                font-weight:bold;
            }
            .toto{
                clear:both;
                margin-left:150px;
                width:125px;
                height:31px;
                background:#666666 url(img/button.png) no-repeat;
                text-align:center;
                line-height:31px;
                color:#FFFFFF;
                font-size:11px;
                font-weight:bold;
            }
        </style>
    </head>
    <body onload='document.f.j_username.focus();'>
        <div class="fullPage">
            <div class="content">
                <h3>Login with Username and Password (Custom Page)</h3>

                <c:if test="${not empty error}">
                    <div class="errorblock">
                        Your login attempt was not successful, try again.<br /> Caused :
                        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                    </div>
                </c:if>
                <div id="stylized" class="myform">
                    <form name='f' action="<c:url value='j_spring_security_check' />"
                          method='POST'>
                        <h1>Login Form</h1>
                        <table>
                            <tr>
                                <td>User:</td>
                                <td><input type='text' name='j_username' value=''>
                                </td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><input type='password' name='j_password' />
                                </td>
                            </tr>
                            <tr>
                                <td colspan='2'><input name="submit" type="submit"
                                                       value="submit" class="toto"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan='2'><input name="reset" type="reset" class="toto"/>
                                </td>
                            </tr>
                        </table>

                    </form>
                </div>
            </div>
        </div>
    </body>
</html>