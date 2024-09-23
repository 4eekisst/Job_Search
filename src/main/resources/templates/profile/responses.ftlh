<#import "../layout.ftlh" as responses>
<@responses.layout ; spring>

    <div class="container mt-4">
        <h2 class="mb-4 text-center">Ваши отклики</h2>

        <#if hasResponses>
            <div class="list-group">
                <#list respondedApplicants as applicant>
                    <div class="list-group-item d-flex justify-content-between align-items-center">
                        <div>
                            <strong>Резюме:</strong> <span class="fw-bold">${applicant.resumeName}</span> <br>
                            <strong>Вакансия:</strong> <span class="fw-bold">${applicant.vacancyName}</span>
                        </div>
                        <#if applicant.confirmation>
                            <span class="badge bg-success rounded-pill">Подтвержден</span>
                        <#else>
                            <span class="badge bg-danger rounded-pill">Не подтвержден</span>
                        </#if>
                    </div>
                </#list>
            </div>
        <#else>
            <div class="alert alert-info text-center" role="alert">
                <strong>Нет откликов</strong>
            </div>
        </#if>
    </div>

</@responses.layout>
