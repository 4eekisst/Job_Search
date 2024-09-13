<#import "../layout.ftlh" as messages>
<@messages.layout ; spring>

    <div class="text-center">

        <h2><@spring.message'message'/></h2>

        <p class="text-warning">${message}</p>

    </div>

</@messages.layout>>