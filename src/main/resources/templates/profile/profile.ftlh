<#import "../layout.ftlh" as profile>
<@profile.layout>
    <div class="profile-container">
        <#if user.authority_id == 1>
            <h2>Личный кабинет соискателя</h2>
        <#else>
            <h2>Личный кабинет работодателя</h2>
        </#if>

        <div class="container my-5 mx-auto"
             style="max-width: 1200px; background: white; border-radius: 15px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
            <div class="row g-3">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    <div class="card border-0 p-3" style="width: 250px;">
                        <div style="width: 200px; height: 200px; overflow: hidden; border-radius: 50%; display: flex; justify-content: center; align-items: center;">
                            <#if user.avatar??>
                                <img src="api/images/image/${user.avatar}" alt="User image"
                                     style="width: 100%; height: auto; object-fit: cover;">
                            <#else>
                                <img src="api/images/image/anon.jpeg" alt="Default user image"
                                     style="width: 100%; height: auto; object-fit: cover;">
                            </#if>
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="card border-0 p-4">
                        <h5 class="card-title">Данные:</h5>
                        <#if user.authority_id == 1>
                            <p class="card-text"><strong>Имя:</strong> ${user.name}</p>
                            <p class="card-text"><strong>Фамилия:</strong> ${user.surname}</p>
                            <p class="card-text">
                                <strong>Возраст:</strong> ${user.age}
                                <#if user.age % 10 == 1 && user.age % 100 != 11>
                                    год
                                <#elseif (user.age % 10 >= 2 && user.age % 10 <= 4) && (user.age % 100 < 10 || user.age % 100 >= 20)>
                                    года
                                <#else>
                                    лет
                                </#if>
                            </p>
                        <#else>
                            <p class="card-text"><strong>Название компании:</strong> ${user.name}</p>
                            <p class="card-text">
                                <strong>Срок деятельности компании:</strong> ${user.age}
                                <#if user.age % 10 == 1 && user.age % 100 != 11>
                                    год
                                <#elseif (user.age % 10 >= 2 && user.age % 10 <= 4) && (user.age % 100 < 10 || user.age % 100 >= 20)>
                                    года
                                <#else>
                                    лет
                                </#if>
                            </p>
                        </#if>

                        <div class="d-grid gap-2 mt-4">
                            <a href="/edit" class="btn btn-secondary btn-sm mb-2"
                               style="width: 200px;">Редактировать профиль</a>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</@profile.layout>
