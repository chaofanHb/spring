<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTDHTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type"content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/css/str.css">
</head>
<script type="text/javascript" src="./jquery-1.8.2.min.js"></script>
<script type="text/javascript">
    function down(url){
        var $a = $("<a></a>").attr("href", url).attr("download", "img.png");
        $a[0].click();
    }
</script>
<body>
<%--<h1 id="str">123456 ${str}</h1>--%>
<input type="button" onclick="down('https://files.chinamye.com.cn/M00/01/57/CgoD-Fq57UWAf547AABG1i40I8I885.jpg')" value="обть" />
</body>
</html>