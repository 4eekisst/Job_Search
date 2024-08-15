<#import "../layout.ftlh" as edit>
<@edit.layout ; spring>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form id="profile-form" action="/edit" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="avatar-input" class="form-label">Выберите изображение</label>
                        <input name="file" class="form-control" type="file" id="avatar-input">
                    </div>

                    <#if authority_id == 1>
                        <div class="mb-3">
                            <label for="name-input" class="form-label">Имя</label>
                            <input name="name" class="form-control" type="text" id="name-input" value="${user.name}">
                        </div>
                        <div class="mb-3">
                            <label for="surname-input" class="form-label">Фамилия</label>
                            <input name="surname" class="form-control" type="text" id="surname-input" value="${user.surname}">
                        </div>
                        <div class="mb-3">
                            <label for="age-input" class="form-label">Какой ваш возраст?</label>
                            <input name="age" class="form-control" type="number" id="age-input" value="${user.age}">
                        </div>
                    <#else>
                        <div class="mb-3">
                            <label for="company-name-input" class="form-label">Название вашей компании</label>
                            <input name="name" class="form-control" type="text" id="company-name-input" value="${user.name}">
                        </div>
                        <div class="mb-3">
                            <label for="company-age-input" class="form-label">Срок деятельности компании</label>
                            <input name="age" class="form-control" type="number" id="company-age-input" value="${user.age}">
                        </div>
                    </#if>

                    <div class="mb-3">
                        <label for="phone-input" class="form-label">Номер телефона</label>
                        <input name="phoneNumber" class="form-control" type="text" id="phone-input" value="${user.phoneNumber}">
                    </div>

                    <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                </form>
                <div class="mt-3">
                    <p class="text-muted">Обратите внимание, что желательно использовать актуальные данные.</p>
                </div>
            </div>
        </div>
    </div>
</@edit.layout>
