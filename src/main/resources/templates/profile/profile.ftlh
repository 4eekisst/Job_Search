<#import "../layout.ftlh" as profile>
<@profile.layout>
    <div class="profile-container">
        <header class="profile-header text-center my-4">
            <#if user.authority_id == 1>
                <h2>Личный кабинет соискателя</h2>
            <#else>
                <h2>Личный кабинет работодателя</h2>
            </#if>
        </header>

        <div class="container my-5 mx-auto" style="max-width: 1200px;">
            <div class="row g-3">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    <div class="card border-0 p-3" style="width: 250px; background: white; border-radius: 15px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
                        <div style="width: 200px; height: 200px; display: flex; justify-content: center; align-items: center; overflow: hidden; border-radius: 50%; background-color: #f0f0f0;">
                            <#if user.avatar??>
                                <img src="api/images/image/${user.avatar}" alt="User image" style="max-width: 100%; max-height: 100%; object-fit: cover;">
                            <#else>
                                <img src="api/images/image/anon.jpeg" alt="Default user image" style="max-width: 100%; max-height: 100%; object-fit: cover;">
                            </#if>
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="card border-0 p-4" style="background: white; border-radius: 15px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
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
                            <a href="/edit" class="btn btn-secondary btn-sm mb-2" style="width: 200px;">Редактировать профиль</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="profile-content mt-4">
                <#if user.authority_id == 1>
                    <h2>Мои резюме</h2>
                    <#if resumes?? && resumes?has_content>
                        <div class="card border-0 p-4" style="background: white; border-radius: 15px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
                            <ul class="list-group">
                                <#list resumes as resume>
                                    <li class="list-group-item">${resume.name} - ${resume.createdDate}</li>
                                </#list>
                            </ul>
                        </div>
                    <#else>
                        <p>У вас нет созданных резюме.</p>
                    </#if>
                <#else>
                    <h2>Мои вакансии</h2>
                    <#if vacancies?? && vacancies?has_content>
                        <div class="card border-0 p-4" style="background: white; border-radius: 15px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
                            <ul class="list-group">
                                <#list vacancies as vacancy>
                                    <li class="list-group-item">${vacancy.name} - ${vacancy.createdDate}</li>
                                </#list>
                            </ul>
                        </div>
                    <#else>
                        <p>У вас нет созданных вакансий.</p>
                    </#if>
                </#if>
            </div>
        </div>
    </div>
</@profile.layout>
