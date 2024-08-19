<#import "../layout.ftlh" as resumeInfo>
<@resumeInfo.layout>

    <main class="container mt-4">
        <div class="row mt-4">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title mb-0">${resume.name}</h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">
                            <strong>Адрес электронной почты:</strong> ${resume.email}
                        </p>
                        <p class="card-text">
                            <strong>Ожидаемый оклад:</strong> $${resume.salary}
                        </p>
                        <p class="card-text">
                            <strong>Статус:</strong> <#if resume.isActive>Активно</#if><#if !resume.isActive>Неактивно</#if>
                        </p>
                        <p class="card-text">
                            <strong>Дата создания:</strong> ${resume.createdDateStr}
                        </p>
                        <p class="card-text">
                            <strong>Дата обновления:</strong> ${resume.updateTimeStr}
                        </p>

                        <#if isApplicant>
                            <a href="/resume/edit/${resume.id}" class="btn btn-primary">Редактировать</a>
                            <a href="/profile" class="btn btn-primary">Вернуться в личный кабинет</a>
                        <#else>
                            <a href="/resumes" class="btn btn-secondary">Вернуться к списку</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </main>

</@resumeInfo.layout>
