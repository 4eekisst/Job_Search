<#import "../layout.ftlh" as profile>
<@profile.layout>
    <div class="container my-5 mx-auto" style="max-width: 1200px;">
        <#if resumesPage?? && resumesPage.content?has_content>
            <div class="row mt-4">
                <#list resumesPage.content as resume>
                    <div class="col-sm-6 mb-4">
                        <div class="card border-0 p-4 bg-white rounded shadow-sm">
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
                                    <strong>Дата создания:</strong> ${resume.createdDateStr}
                                </p>
                                <div class="mt-auto">
                                    <a href="/resume/info/${resume.id}" class="btn btn-primary">Подробнее</a>
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
                            <a class="page-link" href="?page=${resumesPage.number - 1}&size=${resumesPage.size}" aria-label="Previous">
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
                            <a class="page-link" href="?page=${resumesPage.number + 1}&size=${resumesPage.size}" aria-label="Next">
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
            <p>У вас нет созданных резюме.</p>
        </#if>
    </div>
</@profile.layout>
