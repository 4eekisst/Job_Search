<#import "../layout.ftlh" as resumes>
<@resumes.layout ; spring>
    <div class="container my-5 mx-auto" style="max-width: 1200px;">
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
                                    <strong><@spring.message'resumes.email'/></strong> ${resume.email}
                                </p>
                                <p class="card-text">
                                    <strong><@spring.message'resumes.status'/></strong>
                                    <#if resume.isActive>
                                        <@spring.message'resumes.status.active'/>
                                    <#else>
                                        <@spring.message'resumes.status.not.active'/>
                                    </#if>
                                </p>
                                <p class="card-text">
                                    <strong><@spring.message'resumes.created.date'/></strong> ${resume.createdDateStr}
                                </p>
                                <div class="mt-auto">
                                    <a href="/resume/info/${resume.id}" class="btn btn-primary"><@spring.message'resumes.details'/></a>
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
            <p><@spring.message'resumes.not.found'/></p>
        </#if>
    </div>
</@resumes.layout>
