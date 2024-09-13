<#import "../layout.ftlh" as profile>
<@profile.layout ; spring>
    <div class="profile-container">
        <header class="profile-header text-center my-4">
            <#if user.authorityId == 1>
                <h2><@spring.message'profile.applicant'/></h2>
            <#else>
                <h2><@spring.message'profile.employer'/></h2>
            </#if>
        </header>
        <div class="container my-5 mx-auto" style="max-width: 1200px; position: relative;">
            <div class="row g-3">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    <div>
                        <#if user.avatar??>
                            <img src="api/images/image/${user.avatar}" alt="User image"
                                 class="img-fluid rounded-circle"
                                 style="width: 200px; height: 200px; object-fit: cover;">
                        <#else>
                            <img src="api/images/image/anon.jpeg" alt="Default user image"
                                 class="img-fluid rounded-circle"
                                 style="width: 200px; height: 200px; object-fit: cover;">
                        </#if>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="card border-0 p-4 bg-white rounded shadow-sm" style="height: 300px;">
                        <h5 class="card-title"><@spring.message'profile.data'/></h5>
                        <#if user.authorityId == 1>
                            <p class="card-text"><strong><@spring.message'profile.name.surname'/></strong> ${user.name} ${user.surname}</p>
                            <p class="card-text">
                                <strong><@spring.message'profile.age'/></strong> ${user.age}
                                <#if user.age % 10 == 1 && user.age % 100 != 11>
                                    <@spring.message'profile.year'/>
                                <#elseif (user.age % 10 >= 2 && user.age % 10 <= 4) && (user.age % 100 < 10 || user.age % 100 >= 20)>
                                    <@spring.message'profile.years'/>
                                <#else>
                                    <@spring.message'profile.years.old'/>
                                </#if>
                            </p>
                        <#else>
                            <p class="card-text"><strong><@spring.message'profile.company.name'/></strong> ${user.name}</p>
                            <p class="card-text">
                                <strong><@spring.message'profile.company.age'/></strong> ${user.age}
                                <#if user.age % 10 == 1 && user.age % 100 != 11>
                                    <@spring.message'profile.year'/>
                                <#elseif (user.age % 10 >= 2 && user.age % 10 <= 4) && (user.age % 100 < 10 || user.age % 100 >= 20)>
                                    <@spring.message'profile.years'/>
                                <#else>
                                    <@spring.message'profile.years.old'/>
                                </#if>
                            </p>
                        </#if>
                        <div class="d-grid gap-2 mt-4">
                            <a href="/edit" class="btn btn-secondary btn-sm mb-2" style="width: 200px;"><@spring.message'profile.edit'/></a>
                        </div>
                    </div>
                </div>
            </div>
            <#if user.authorityId == 1>
                <a href="/resume/create"
                   class="btn btn-success position-absolute d-flex align-items-center justify-content-center"
                   style="width: 70px; height: 70px; background-color: gray; border: 2px solid gray; top: 100px; right: 100px; color: white; font-size: 36px; border-radius: 5px;">
                    +
                </a>
            <#else>
                <a href="/vacancy/create"
                   class="btn btn-success position-absolute d-flex align-items-center justify-content-center"
                   style="width: 70px; height: 70px; background-color: gray; border: 2px solid gray; top: 100px; right: 100px; color: white; font-size: 36px; border-radius: 5px;">
                    +
                </a>
            </#if>
            <div class="profile-content mt-4">
                <#if user.authorityId == 1>
                    <h2><@spring.message'profile.resumes'/></h2>
                    <#if resumesPage?? && resumesPage.content?has_content>
                        <div class="row mt-4">
                            <#list resumesPage.content as resume>
                                <div class="col-sm-6 mb-4">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="card-title mb-0">${resume.name}</h5>
                                        </div>
                                        <div class="card-body">
                                            <p class="card-text">
                                                <strong><@spring.message'profile.resume.email'/></strong> ${resume.email}
                                            </p>
                                            <p class="card-text">
                                                <strong><@spring.message'profile.resume.status'/></strong> <#if resume.isActive>Активно</#if><#if !resume.isActive>Неактивно</#if>
                                            </p>
                                            <p class="card-text">
                                                <strong><@spring.message'profile.resume.date'/></strong> ${resume.createdDateStr}
                                            </p>
                                            <div class="mt-auto">
                                                <form action="/resume/update-time" method="post"
                                                      style="display:inline;">
                                                    <#if _csrf??>
                                                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                                               value="${(_csrf.token)!'csrf-token'}"/>
                                                    </#if>
                                                    <input type="hidden" name="id" value="${resume.id}">
                                                    <button type="submit" class="btn btn-secondary"><@spring.message'profile.resume.update'/></button>
                                                </form>
                                                <a href="/resume/edit/${resume.id}" class="btn btn-secondary"><@spring.message'profile.resume.edit'/></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                        <nav aria-label="Page navigation" class="mt-4">
                            <ul class="pagination justify-content-center">
                                <#if resumesPage.hasPrevious()>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="?page=${resumesPage.number - 1}&size=${resumesPage.size}"
                                           aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                <#else>
                                    <li class="page-item disabled">
                                        <span class="page-link">&laquo;</span>
                                    </li>
                                </#if>
                                <#list 0..(resumesPage.totalPages - 1) as i>
                                    <li class="page-item <#if i == resumesPage.number>active</#if>">
                                        <a class="page-link" href="?page=${i}&size=${resumesPage.size}">${i + 1}</a>
                                    </li>
                                </#list>
                                <#if resumesPage.hasNext()>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="?page=${resumesPage.number + 1}&size=${resumesPage.size}"
                                           aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                <#else>
                                    <li class="page-item disabled">
                                        <span class="page-link">&raquo;</span>
                                    </li>
                                </#if>
                            </ul>
                        </nav>
                    <#else>
                        <p><@spring.message'profile.resume.not.found'/></p>
                    </#if>
                <#else>
                    <h2><@spring.message'profile.vacancies'/></h2>
                    <#if vacanciesPage?? && vacanciesPage.content?has_content>
                        <div class="row mt-4">
                            <#list vacanciesPage.content as vacancy>
                                <div class="col-sm-6 mb-4">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="card-title mb-0">${vacancy.name}</h5>
                                        </div>
                                        <div class="card-body">
                                            <p class="card-text">
                                                <strong><@spring.message'profile.vacancy.exp'/></strong>
                                                <#if vacancy.expFrom == vacancy.expTo>
                                                    <#if vacancy.expFrom % 10 == 1 && vacancy.expFrom % 100 != 11>
                                                        ${vacancy.expFrom} <@spring.message'profile.year'/>
                                                    <#elseif (vacancy.expFrom % 10 >= 2 && vacancy.expFrom % 10 <= 4) && (vacancy.expFrom % 100 < 10 || vacancy.expFrom % 100 >= 20)>
                                                        ${vacancy.expFrom} <@spring.message'profile.years'/>
                                                    <#else>
                                                        ${vacancy.expFrom} <@spring.message'profile.years.old'/>
                                                    </#if>
                                                <#else>
                                                    <#if vacancy.expFrom % 10 == 1 && vacancy.expFrom % 100 != 11>
                                                        ${vacancy.expFrom} <@spring.message'profile.year'/>
                                                    <#elseif (vacancy.expFrom % 10 >= 2 && vacancy.expFrom % 10 <= 4) && (vacancy.expFrom % 100 < 10 || vacancy.expFrom % 100 >= 20)>
                                                        ${vacancy.expFrom} <@spring.message'profile.years'/>
                                                    <#else>
                                                        ${vacancy.expFrom} <@spring.message'profile.years.old'/>
                                                    </#if>
                                                    -
                                                    <#if vacancy.expTo % 10 == 1 && vacancy.expTo % 100 != 11>
                                                        ${vacancy.expTo} <@spring.message'profile.year'/>
                                                    <#elseif (vacancy.expTo % 10 >= 2 && vacancy.expTo % 10 <= 4) && (vacancy.expTo % 100 < 10 || vacancy.expTo % 100 >= 20)>
                                                        ${vacancy.expTo} <@spring.message'profile.years'/>
                                                    <#else>
                                                        ${vacancy.expTo} <@spring.message'profile.years.old'/>
                                                    </#if>
                                                </#if>
                                            </p>
                                            <p class="card-text">${vacancy.description}</p>
                                            <div class="mt-auto">
                                                <form action="/vacancy/update-time" method="post"
                                                      style="display:inline;">
                                                    <#if _csrf??>
                                                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                                               value="${(_csrf.token)!'csrf-token'}"/>
                                                    </#if>
                                                    <input type="hidden" name="id" value="${vacancy.id}">
                                                    <button type="submit" class="btn btn-secondary"><@spring.message'profile.vacancy.update'/></button>
                                                </form>
                                                <a href="/vacancy/edit/${vacancy.id}" class="btn btn-secondary"><@spring.message'profile.vacancy.edit'/></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                        <nav aria-label="Page navigation" class="mt-4">
                            <ul class="pagination justify-content-center">
                                <#if vacanciesPage.hasPrevious()>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="?page=${vacanciesPage.number - 1}&size=${vacanciesPage.size}"
                                           aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                <#else>
                                    <li class="page-item disabled">
                                        <span class="page-link">&laquo;</span>
                                    </li>
                                </#if>

                                <#list 0..(vacanciesPage.totalPages - 1) as i>
                                    <li class="page-item <#if i == vacanciesPage.number>active</#if>">
                                        <a class="page-link" href="?page=${i}&size=${vacanciesPage.size}">${i + 1}</a>
                                    </li>
                                </#list>

                                <#if vacanciesPage.hasNext()>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="?page=${vacanciesPage.number + 1}&size=${vacanciesPage.size}"
                                           aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                <#else>
                                    <li class="page-item disabled">
                                        <span class="page-link">&raquo;</span>
                                    </li>
                                </#if>
                            </ul>
                        </nav>
                    <#else>
                        <p><@spring.message'profile.vacancy.not.found'/></p>
                    </#if>
                </#if>
            </div>
        </div>
    </div>
</@profile.layout>
