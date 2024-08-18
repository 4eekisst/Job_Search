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
                    <div>
                        <#if user.avatar??>
                            <img src="api/images/image/${user.avatar}" alt="User image"
                                 class="img-fluid rounded-circle" style="width: 200px; height: 200px; object-fit: cover;">
                        <#else>
                            <img src="api/images/image/anon.jpeg" alt="Default user image"
                                 class="img-fluid rounded-circle" style="width: 200px; height: 200px; object-fit: cover;">
                        </#if>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="card border-0 p-4 bg-white rounded shadow-sm" style="height: 300px;">
                        <h5 class="card-title">Данные:</h5>
                        <#if user.authority_id == 1>
                            <p class="card-text"><strong>Имя Фамилия:</strong> ${user.name} ${user.surname}</p>
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

            <div class="d-flex justify-content-end mb-4">
                <#if user.authority_id == 1>
                    <a href="/resume/create" class="btn btn-success rounded-circle" style="width: 50px; height: 50px; display: flex; align-items: center; justify-content: center; font-size: 24px;">
                        +
                    </a>
                <#else>
                    <a href="/vacancy/create" class="btn btn-success rounded-circle" style="width: 50px; height: 50px; display: flex; align-items: center; justify-content: center; font-size: 24px;">
                        +
                    </a>
                </#if>
            </div>

            <div class="profile-content mt-4">
                <#if user.authority_id == 1>
                    <h2>Резюме:</h2>
                    <#if resumes?? && resumes?has_content>
                        <div class="row mt-4">
                            <#list resumes as resume>
                                <div class="col-sm-6 mb-4">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="card-title mb-0">${resume.name}</h5>
                                        </div>
                                        <div class="card-body">
                                            <p class="card-text">
                                                <strong>Адрес электронной почты:</strong> ${resume.email}
                                            </p>
                                            <p class="card-text">
                                                <strong>Статус:</strong> <#if resume.isActive>Активно</#if><#if !resume.isActive>Неактивно</#if>
                                            </p>
                                            <p class="card-text">
                                                <strong>Дата создания:</strong> ${resume.createdDate?date("yyyy-MM-dd")}
                                            </p>
                                            <a href="/resume/info/${resume.id}" class="btn btn-primary">Подробнее</a>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    <#else>
                        <p>У вас нет созданных резюме.</p>
                    </#if>
                <#else>
                    <h2>Созданные вакансии:</h2>
                    <#if vacancies?? && vacancies?has_content>
                        <div class="row mt-4">
                            <#list vacancies as vacancy>
                                <div class="col-sm-6 mb-4">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="card-title mb-0">${vacancy.name}</h5>
                                        </div>
                                        <div class="card-body">
                                            <p class="card-text">
                                                <strong>Требуемый опыт:</strong>
                                                <#if vacancy.expFrom == vacancy.expTo>
                                                    <#if vacancy.expFrom % 10 == 1 && vacancy.expFrom % 100 != 11>
                                                        ${vacancy.expFrom} год
                                                    <#elseif (vacancy.expFrom % 10 >= 2 && vacancy.expFrom % 10 <= 4) && (vacancy.expFrom % 100 < 10 || vacancy.expFrom % 100 >= 20)>
                                                        ${vacancy.expFrom} года
                                                    <#else>
                                                        ${vacancy.expFrom} лет
                                                    </#if>
                                                <#else>
                                                    <#if vacancy.expFrom % 10 == 1 && vacancy.expFrom % 100 != 11>
                                                        ${vacancy.expFrom} год
                                                    <#elseif (vacancy.expFrom % 10 >= 2 && vacancy.expFrom % 10 <= 4) && (vacancy.expFrom % 100 < 10 || vacancy.expFrom % 100 >= 20)>
                                                        ${vacancy.expFrom} года
                                                    <#else>
                                                        ${vacancy.expFrom} лет
                                                    </#if>
                                                    -
                                                    <#if vacancy.expTo % 10 == 1 && vacancy.expTo % 100 != 11>
                                                        ${vacancy.expTo} год
                                                    <#elseif (vacancy.expTo % 10 >= 2 && vacancy.expTo % 10 <= 4) && (vacancy.expTo % 100 < 10 || vacancy.expTo % 100 >= 20)>
                                                        ${vacancy.expTo} года
                                                    <#else>
                                                        ${vacancy.expTo} лет
                                                    </#if>
                                                </#if>
                                            </p>
                                            <p class="card-text">${vacancy.description}</p>
                                            <div class="mt-auto">
                                                <form action="/vacancy/update-time" method="post" style="display:inline;">
                                                    <input type="hidden" name="id" value="${vacancy.id}">
                                                    <button type="submit" class="btn btn-secondary">Обновить</button>
                                                </form>
                                                <a href="/vacancy/edit/${vacancy.id}" class="btn btn-secondary">Редактировать</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    <#else>
                        <p>У вас нет созданных вакансий.</p>
                    </#if>
                </#if>
            </div>
        </div>
    </div>
</@profile.layout>
