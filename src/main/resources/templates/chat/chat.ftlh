<#import "../layout.ftlh" as chat>
<@chat.layout ; spring>
    <link rel="stylesheet" href="/chat.css">
    <body>
    <div id="username-page">
        <div class="username-page-container">
            <h1 class="title">Entering the Chat...</h1>
            <form id="usernameForm" name="usernameForm">

            </form>
        </div>
    </div>

    <div id="chat-page" class="hidden">
        <input type="hidden" id="user-email" value="${email}"/>

        <div class="chat-container">
            <div class="chat-header">
                <h2>Чат с работодателем</h2>
            </div>
            <div class="connecting">
                Connecting...
            </div>
            <ul id="messageArea">

            </ul>
            <form id="messageForm" name="messageForm">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>
                <div class="form-group">
                    <div class="input-group clearfix">
                        <input type="text" id="message" placeholder="Введите сообщение..." autocomplete="off" class="form-control"/>
                        <button type="submit" class="primary">Отправить</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </body>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/chat.js"></script>
</@chat.layout>
