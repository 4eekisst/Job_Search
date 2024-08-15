<#import "../layout.ftlh" as register>
<@register.layout>

    <div class="container">
        <#assign isEmployer = isEmployer!false>
        <#assign authorityId = userDto.authority_id!1>

        <form action="/auth/register" method="post">
            <div class="form-group">
                <label for="authority_id">Выберите ваш тип регистрации:</label>
                <select id="authority_id" name="authority_id" class="form-control" onchange="this.form.submit()">
                    <option value="1" <#if authorityId == 1>selected</#if>>Регистрация соискателя</option>
                    <option value="2" <#if authorityId == 2>selected</#if>>Регистрация для поиска сотрудников</option>
                </select>
            </div>

            <#if authorityId == 2>
                <div class="form-group">
                    <label for="name">Название компании:</label>
                    <input type="text" id="name" name="name" class="form-control" value="${userDto.name!''}" required>
                </div>

                <div class="form-group">
                    <label for="age">Сколько лет вашей компании:</label>
                    <input type="number" id="age" name="age" class="form-control" value="${userDto.age!''}" required>
                </div>
            <#else>
                <div class="form-group">
                    <label for="name">Имя:</label>
                    <input type="text" id="name" name="name" class="form-control" value="${userDto.name!''}" required>
                </div>

                <div class="form-group">
                    <label for="surname">Фамилия:</label>
                    <input type="text" id="surname" name="surname" class="form-control" value="${userDto.surname!''}" required>
                </div>

                <div class="form-group">
                    <label for="age">Возраст:</label>
                    <input type="number" id="age" name="age" class="form-control" value="${userDto.age!''}" required>
                </div>
            </#if>

            <div class="form-group">
                <label for="email">Адрес электронной почты:</label>
                <input type="email" id="email" name="email" class="form-control" value="${userDto.email!''}" required>
            </div>

            <div class="form-group">
                <label for="password">Создайте надежный пароль:</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="phoneNumber">Ваш контактный номер:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" value="${userDto.phoneNumber!''}">
            </div>

            <button type="submit" class="btn btn-primary">Подтвердить регистрацию</button>
        </form>
    </div>

</@register.layout>
