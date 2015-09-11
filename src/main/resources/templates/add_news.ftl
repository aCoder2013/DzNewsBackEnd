<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>XX新闻后台</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://cdn.bootcss.com/jquery/2.1.3/jquery.js"></script>
    <!--simditor JavaScript-->
    <script type="text/javascript" src="../js/module.js"></script>
    <script type="text/javascript" src="../js/hotkeys.js"></script>
    <script type="text/javascript" src="../js/uploader.js"></script>
    <script type="text/javascript" src="../js/simditor.js"></script>
    <!--Simditor CSS-->
    <link rel="stylesheet" type="text/css" href="../css/simditor.css" />
    <!-- Custom CSS -->
    <link href="../../css/clean-blog.min.css" rel="stylesheet">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">独自存货</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-expanded="false">${user.name} <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu" >
                        <li><a href="/news/addNewsPage" style="color:black;">写新闻</a></li>
                        <li><a href="#" style="color:black;">我的主页</a></li>
                        <li><a href="#" style="color:black;">提醒</a></li>
                        <li><a href="#" style="color:black;">设置</a></li>
                        <li class="divider" style="color:black;"></li>
                        <li><a href="#" style="color:black;">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->
<header class="intro-header" style="background-image: url('../../img/home-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="site-heading">
                    <h1>独自存货</h1>
                    <hr class="small">
                    <span class="subheading">网罗天下大事,享受一站式阅读</span>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <div>
        <p>
        <h1>添加新闻</h1>
        </p>
    </div>
    <form action="/news/addNews" role="form"  method="post" >
    <#if (news.id)??>
        <input type="hidden" name="id" value="${(news.id)}"/>
    </#if>
        <div>
            <input type="text" name="title" class="form-control " placeholder="请输入新闻标题" value="${(news.title)!''}" aria-describedby="sizing-addon2">
        </div>
        <div class="divider"></div>
        <div>
            <input type="text" name="fromPublisher" placeholder="发稿机构" value="${(news.fromPublisher)!''}" />
        </div>
    <#if (news.publishTime)??>
        <div>
            <input type="hidden"  name="publishTime" value="${(news.publishTime)}"/>
        </div>
    </#if>
        <input type="hidden" name="beenRead" value="${(news.beenRead)!0}" />
        <div>
            <p>
                <textarea id="content" name="content" placeholder="这里输入内容"  autofocus></textarea>
            </p>
        </div>
        <div>
            <p>
                <#--<input type="submit" value="发表新闻" class="btn btn-primary" />-->
                <Button type="submit" class="btn btn-primary">发表新闻</Button>
            </p>
        </div>
    </form>
</div>
<script language="JavaScript">
    var editor = new Simditor({
        textarea: $('#content'),
        pasteImage:true,
        imageButton:['external','upload'],
        upload: {
            url: '/news/uploadImg',
            params: null,
            fileKey: 'upload_file',
            connectionCount: 3,
            leaveConfirm: '正在上传文件，如果离开上传会自动取消'
        },
        toolbar:[
            'title',
            'bold',
            'italic',
            'underline',
            'strikethrough',
            'color',
            'ol',
            'ul',
            'blockquote',
            'code',
            'table',
            'link',
            'image',
            'hr',
            'indent',
            'outdent',
            'alignment'
        ]
    });
    editor.setValue('${((content))!''}');
</script>






</body>
</html>