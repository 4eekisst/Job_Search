<#import "../layout.ftlh" as listOfResume>
<@listOfResume.layout>

    <main class="container mt-4">
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
    </main>

</@listOfResume.layout>
