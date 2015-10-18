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
    <title>${news.title}</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="images/touch/chrome-touch-icon-192x192.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="apple-touch-icon-precomposed.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
    <link rel="canonical" href="http://www.example.com/">
    -->

    <link href='//fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
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
    </style>
</head>
<body>
<div class="demo-blog demo-blog--blogpost mdl-layout mdl-js-layout has-drawer is-upgraded">
    <main class="mdl-layout__content">
        <div class="demo-back">
            <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" href="/" title="go back" role="button">
                <i class="material-icons" role="presentation">arrow_back</i>
            </a>
        </div>
        <div class="demo-blog__posts mdl-grid">
            <div class="mdl-card mdl-shadow--4dp mdl-cell mdl-cell--12-col">
                <div class="mdl-card__media mdl-color-text--grey-50">
                    <h3>${news.title}</h3>
                </div>
                <div class="mdl-color-text--grey-700 mdl-card__supporting-text meta">
                    <div class="minilogo"></div>
                    <div>
                        <strong>${(news.auth)!'匿名'}</strong>
                        <span>${(news.pubTime)!}</span>
                    </div>
                    <div class="section-spacer"></div>
                    <div class="meta__favorites">
                    ${news.beenRead!157} <i class="material-icons" role="presentation">favorite</i>
                        <span class="visuallyhidden">favorites</span>
                    </div>
                    <div>
                        <i class="material-icons" role="presentation">bookmark</i>
                        <span class="visuallyhidden">bookmark</span>
                    </div>
                    <div>
                        <i class="material-icons" role="presentation">share</i>
                        <span class="visuallyhidden">share</span>
                    </div>
                </div>
                <div class="mdl-color-text--grey-700 mdl-grid">
                    <div class="mdl-cell mdl-cell--1-col"></div>
                    <div class="mdl-cell mdl-cell--8-col">
                         ${news.content}
                    </div>
                    <div class="mdl-cell mdl-cell--1-col"></div>
                </div>
                <div class="mdl-color-text--primary-contrast mdl-card__supporting-text comments">
                    <form>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <textarea rows=1 class="mdl-textfield__input" id="comment"></textarea>
                            <label for="comment" class="mdl-textfield__label">Join the discussion</label>
                        </div>
                        <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                            <i class="material-icons" role="presentation">check</i><span class="visuallyhidden">add comment</span>
                        </button>
                    </form>
                    <div class="comment mdl-color-text--grey-700">
                        <header class="comment__header">
                            <img src="../img/co1.jpg" class="comment__avatar">
                            <div class="comment__author">
                                <strong>James Splayd</strong>
                                <span>2 days ago</span>
                            </div>
                        </header>
                        <div class="comment__text">
                            In in culpa nulla elit esse. Ex cillum enim aliquip sit sit ullamco ex eiusmod fugiat. Cupidatat ad minim officia mollit laborum magna dolor tempor cupidatat mollit. Est velit sit ad aliqua ullamco laborum excepteur dolore proident incididunt in labore elit.
                        </div>
                        <nav class="comment__actions">
                            <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                                <i class="material-icons" role="presentation">thumb_up</i><span class="visuallyhidden">like comment</span>
                            </button>
                            <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                                <i class="material-icons" role="presentation">thumb_down</i><span class="visuallyhidden">dislike comment</span>
                            </button>
                            <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                                <i class="material-icons" role="presentation">share</i><span class="visuallyhidden">share comment</span>
                            </button>
                        </nav>
                        <div class="comment__answers">
                            <div class="comment">
                                <header class="comment__header">
                                    <img src="/img/co2.jpg" class="comment__avatar">
                                    <div class="comment__author">
                                        <strong>John Dufry</strong>
                                        <span>2 days ago</span>
                                    </div>
                                </header>
                                <div class="comment__text">
                                    Yep, agree!
                                </div>
                                <nav class="comment__actions">
                                    <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                                        <i class="material-icons" role="presentation">thumb_up</i><span class="visuallyhidden">like comment</span>
                                    </button>
                                    <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                                        <i class="material-icons" role="presentation">thumb_down</i><span class="visuallyhidden">dislike comment</span>
                                    </button>
                                    <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                                        <i class="material-icons" role="presentation">share</i><span class="visuallyhidden">share comment</span>
                                    </button>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <nav class="demo-nav mdl-color-text--grey-50 mdl-cell mdl-cell--12-col">
                <a href="index.html" class="demo-nav__button">
                    <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon mdl-color--white mdl-color-text--grey-900" role="presentation">
                        <i class="material-icons">arrow_back</i>
                    </button>
                    Newer
                </a>
                <div class="section-spacer"></div>
                <a href="index.html" class="demo-nav__button">
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
<script src="../js/material.min.js"></script>
</body>
</html>
