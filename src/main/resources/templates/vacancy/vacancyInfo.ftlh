<#import "../layout.ftlh" as vacancyInfo>
<@vacancyInfo.layout ; spring>

<main class="container mt-4">
    <div class="row mt-4">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title mb-0">${vacancy.name}</h5>
                </div>
                <div class="card-body">
                    <p class="card-text">
                        <strong><@spring.message'vacancyInfo.exp'/></strong>
                        <#if vacancy.expFrom == vacancy.expTo>
                            <#if vacancy.expFrom % 10 == 1 && vacancy.expFrom % 100 != 11>
                                ${vacancy.expFrom} <@spring.message'vacancyInfo.year'/>
                            <#elseif (vacancy.expFrom % 10 >= 2 && vacancy.expFrom % 10 <= 4) && (vacancy.expFrom % 100 < 10 || vacancy.expFrom % 100 >= 20)>
                                ${vacancy.expFrom} <@spring.message'vacancyInfo.years'/>
                            <#else>
                                ${vacancy.expFrom} <@spring.message'vacancyInfo.years.old'/>
                            </#if>
                        <#else>
                            <#if vacancy.expFrom % 10 == 1 && vacancy.expFrom % 100 != 11>
                                ${vacancy.expFrom} <@spring.message'vacancyInfo.year'/>
                            <#elseif (vacancy.expFrom % 10 >= 2 && vacancy.expFrom % 10 <= 4) && (vacancy.expFrom % 100 < 10 || vacancy.expFrom % 100 >= 20)>
                                ${vacancy.expFrom} <@spring.message'vacancyInfo.years'/>
                            <#else>
                                ${vacancy.expFrom} <@spring.message'vacancyInfo.years.old'/>
                            </#if>
                            -
                            <#if vacancy.expTo % 10 == 1 && vacancy.expTo % 100 != 11>
                                ${vacancy.expTo} <@spring.message'vacancyInfo.year'/>
                            <#elseif (vacancy.expTo % 10 >= 2 && vacancy.expTo % 10 <= 4) && (vacancy.expTo % 100 < 10 || vacancy.expTo % 100 >= 20)>
                                ${vacancy.expTo} <@spring.message'vacancyInfo.years'/>
                            <#else>
                                ${vacancy.expTo} <@spring.message'vacancyInfo.years.old'/>
                            </#if>
                        </#if>
                    </p>
                    <p class="card-text">${vacancy.description}</p>
                    <p class="card-text"><strong><@spring.message'vacancyInfo.salary'/></strong> $${vacancy.salary}</p>
                    <#if fromEditOrCreate>
                        <a href="/vacancy/edit/${vacancy.id}" class="btn btn-primary"><@spring.message 'vacancyInfo.edit'/></a>
                        <a href="/profile" class="btn btn-primary"><@spring.message 'vacancyInfo.profile'/></a>
                    <#else>
                        <#if isAuthenticated && isApplicant>
                            <#if hasResponded>
                                <p class="text-warning"><@spring.message'vacancyInfo.has.respond'/></p>
                            <#else>
                                <button class="btn btn-primary" data-toggle="modal" data-target="#respondModal"><@spring.message 'vacancyInfo.respond.button'/></button>
                            </#if>
                            <a href="/vacancies" class="btn btn-secondary"><@spring.message 'vacancyInfo.back.to.list'/></a>
                        <#elseif isAuthenticated>
                            <a href="/vacancies" class="btn btn-secondary"><@spring.message 'vacancyInfo.back.to.list'/></a>
                        <#else>
                            <a href="/auth/login" class="btn btn-primary"><@spring.message 'vacancyInfo.respond.button'/></a>
                            <a href="/vacancies" class="btn btn-secondary"><@spring.message 'vacancyInfo.back.to.list'/></a>
                        </#if>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</main>

<div class="modal fade" id="respondModal" tabindex="-1" role="dialog" aria-labelledby="respondModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="respondModalLabel"><@spring.message'vacancyInfo.modal.respond.text'/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/respond" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>
                <input type="hidden" name="vacancyId" value="${vacancy.id}"/>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="resumeSelect"><@spring.message'vacancyInfo.choose.resume'/></label>
                        <select id="resumeSelect" name="resumeId" class="form-control">
                            <#list userResumes as resume>
                                <option value="${resume.id}">${resume.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="message"><@spring.message'vacancyInfo.modal.text'/></label>
                        <textarea id="message" name="message" class="form-control" rows="4"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><@spring.message'vacancyInfo.exit'/></button>
                    <button type="submit" class="btn btn-primary"><@spring.message'vacancyInfo.respond'/></button>
                </div>
            </form>
        </div>
    </div>
</div>

</@vacancyInfo.layout>
