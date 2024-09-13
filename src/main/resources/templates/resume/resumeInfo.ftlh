<#import "../layout.ftlh" as resumeInfo>
<@resumeInfo.layout ; spring>
    <main class="container mt-4">
        <div class="row mt-4">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title mb-0">${resume.name}</h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">
                            <strong><@spring.message'resume.info.email'/></strong> ${resume.email}
                        </p>
                        <p class="card-text">
                            <strong><@spring.message'resume.info.salary'/></strong> $${resume.salary}
                        </p>
                        <p class="card-text">
                            <strong><@spring.message'resume.info.status'/></strong> <#if resume.isActive><@spring.message'resume.info.active'/></#if><#if !resume.isActive><@spring.message'resume.info.not.active'/></#if>
                        </p>
                        <p class="card-text">
                            <strong><@spring.message'resume.info.created.date'/></strong> ${resume.createdDateStr}
                        </p>
                        <p class="card-text">
                            <strong><@spring.message'resume.info.update'/></strong> ${resume.updateTimeStr}
                        </p>

                        <#if fromEditOrCreate>
                            <a href="/resume/edit/${resume.id}" class="btn btn-primary"><@spring.message'resume.info.edit'/></a>
                            <a href="/profile" class="btn btn-primary"><@spring.message'resume.info.profile'/></a>
                        <#else>
                            <a href="/resumes" class="btn btn-secondary"><@spring.message'resume.info.list'/></a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </main>
</@resumeInfo.layout>
