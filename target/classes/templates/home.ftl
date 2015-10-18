<!doctype html>
<!--
  Material Design Lite
  Copyright 2015 Google Inc. All rights reserved.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License
-->
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DzNews</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="../img/android-desktop.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="images/ios-desktop.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <link rel="shortcut icon" href="../img/favicon.png" />

    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
    <link rel="canonical" href="http://www.example.com/">
    -->

    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="../css/material.min.css">
    <link rel="stylesheet" href="../css/styles.css">
    <style>
        #view-source {
            position: fixed;
            display: block;
            right: 0;
            bottom: 0;
            margin-right: 40px;
            margin-bottom: 40px;
            z-index: 900;
        }
        <!-- Uses a transparent header that draws on top of the layout's background -->

        .demo-layout-transparent {

        }
        .demo-layout-transparent .mdl-layout__header,
        .demo-layout-transparent .mdl-layout__drawer-button  {
            /* This background is dark, so we set text to white. Use 87% black instead if
               your background is light. */
            color: white;
        }
        .mdl-layout_nav_link{
            color: black;
        }
    </style>
</head>
<body>
<div class="demo-layout-transparent mdl-layout mdl-js-layout">
    <header class="mdl-layout__header mdl-layout__header--transparent">
        <div class="mdl-layout__header-row">
            <!-- Title -->
            <span class="mdl-layout-title">DzNews</span>
            <!-- Add spacer, to align navigation to the right -->
            <div class="mdl-layout-spacer"></div>
        </div>
    </header>
    <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">DzNews</span>

        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="/">主页</a>
            <a class="mdl-navigation__link" href="">新闻</a>
            <a class="mdl-navigation__link" href="">通知</a>
            <a class="mdl-navigation__link" href="">发现</a>
        </nav>
    </div>
    <div class="demo-blog mdl-layout mdl-js-layout has-drawer is-upgraded">
        <main class="mdl-layout__content">
            <div class="demo-blog__posts mdl-grid">
                <#list newsList as news>
                    <div class="mdl-card amazing mdl-cell mdl-cell--12-col">
                        <div class="mdl-card__title mdl-color-text--grey-50">
                            <h3 class=""><a href="/news/${news.id?c}">${news.title}</a></h3>
                        </div>
                        <div class="mdl-card__supporting-text mdl-color-text--grey-600">
                        ${news.description}
                        </div>
                        <div class="mdl-card__supporting-text meta mdl-color-text--grey-600">
                            <div class="minilogo"></div>
                            <div>
                                <strong>The Newist</strong>
                                <span>2 days ago</span>
                            </div>
                        </div>
                    </div>
                </#list>

                    <nav class="demo-nav mdl-color-text--grey-50 mdl-cell mdl-cell--12-col">
                        <a href="?page=${pagenum-1}" class="demo-nav__button">
                            <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon mdl-color--white mdl-color-text--grey-900" role="presentation">
                                <i class="material-icons">arrow_back</i>
                            </button>
                            Newer
                        </a>
                        <div class="section-spacer"></div>
                        <a href="?page=${pagenum+1}" class="demo-nav__button">
                            Older
                            <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon mdl-color--white mdl-color-text--grey-900" role="presentation">
                                <i class="material-icons">arrow_forward</i>
                            </button>
                        </a>
                    </nav>

            </div>
            <footer class="mdl-mini-footer">
                <div class="mdl-mini-footer--left-section">
                    <button class="mdl-mini-footer--social-btn social-btn social-btn__twitter">
                        <span class="visuallyhidden">Twitter</span>
                    </button>
                    <button class="mdl-mini-footer--social-btn social-btn social-btn__blogger">
                        <span class="visuallyhidden">Facebook</span>
                    </button>
                    <button class="mdl-mini-footer--social-btn social-btn social-btn__gplus">
                        <span class="visuallyhidden">Google Plus</span>
                    </button>
                </div>
                <div class="mdl-mini-footer--right-section">
                    <button class="mdl-mini-footer--social-btn social-btn__share">
                        <i class="material-icons" role="presentation">share</i>
                        <span class="visuallyhidden">share</span>
                    </button>
                </div>
            </footer>
        </main>
        <div class="mdl-layout__obfuscator"></div>
    </div>
</div>
<script src="../js/material.min.js"></script>
</body>
<script>
    Array.prototype.forEach.call(document.querySelectorAll('.mdl-card__media'), function(el) {
        var link = el.querySelector('a');
        if(!link) {
            return;
        }
        var target = link.getAttribute('href');
        if(!target) {
            return;
        }
        el.addEventListener('click', function() {
            location.href = target;
        });
    });
</script>
</html>
