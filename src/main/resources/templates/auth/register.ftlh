<#import "../layout.ftlh" as register>
<@register.layout>

    <div class="container">
        <#assign isEmployer = isEmployer!false>
        <#assign authorityId = userDto.authorityId!1>

        <form action="/auth/register" method="post" id="registerForm">
            <div class="form-group">
                <label for="authorityId">Выберите ваш тип регистрации:</label>
                <select id="authorityId" name="authorityId" class="form-control" onchange="updateFormFields()">
                    <option value="1" <#if authorityId == 1>selected</#if>>Регистрация соискателя</option>
                    <option value="2" <#if authorityId == 2>selected</#if>>Регистрация для поиска сотрудников</option>
                </select>
            </div>

            <div id="dynamicFields">
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
            </div>

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

        <script>
            function updateFormFields() {
                const authorityId = document.getElementById('authorityId').value;
                const dynamicFields = document.getElementById('dynamicFields');

                if (authorityId == '2') {
                    dynamicFields.innerHTML = `
                        <div class="form-group">
                            <label for="name">Название компании:</label>
                            <input type="text" id="name" name="name" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="age">Сколько лет вашей компании:</label>
                            <input type="number" id="age" name="age" class="form-control" required>
                        </div>
                    `;
                } else {
                    dynamicFields.innerHTML = `
                        <div class="form-group">
                            <label for="name">Имя:</label>
                            <input type="text" id="name" name="name" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="surname">Фамилия:</label>
                            <input type="text" id="surname" name="surname" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="age">Возраст:</label>
                            <input type="number" id="age" name="age" class="form-control" required>
                        </div>
                    `;
                }
            }
            document.addEventListener('DOMContentLoaded', updateFormFields);
        </script>
    </div>

</@register.layout>
