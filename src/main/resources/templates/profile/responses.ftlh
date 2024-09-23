<#import "../layout.ftlh" as responses>
<@responses.layout ; spring>

    <div class="container mt-4">
        <h2 class="mb-4 text-center"><@spring.message'responses'/></h2>

        <#if hasResponses>
            <div class="row">
                <#list respondedApplicants as applicant>
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${applicant.resumeName}</h5>
                                <p class="card-text">
                                    <strong><@spring.message'responses.vacancy'/>:</strong> ${applicant.vacancyName} <br>
                                    <#if applicant.confirmation>
                                        <span class="badge bg-success rounded-pill"><@spring.message'responses.confirm'/></span>
                                    <#else>
                                        <span class="badge bg-danger rounded-pill"><@spring.message'responses.not.confirm'/></span>
                                    </#if>
                                </p>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        <#else>
            <div class="alert alert-info text-center" role="alert">
                <strong><@spring.message'responses.not.responses'/></strong>
            </div>
        </#if>
    </div>

</@responses.layout>
