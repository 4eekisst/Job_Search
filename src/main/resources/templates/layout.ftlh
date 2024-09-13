<#import "spring.ftl" as spring>
<#macro layout>

    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JobSearch</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .navbar-text {
                margin-left: auto;
            }
        </style>
    </head>
    <body>
    <#assign
    known = SPRING_SECURITY_CONTEXT??
    >
    <nav class="navbar navbar-expand-md navbar-dark bg-primary pt-3 pb-3">
        <div class="container">
            <a class="navbar-brand" href="/">Job Search</a>
            <button class="navbar-toggler text-light bg-orange btn-user" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav"
                    aria-expanded="false" aria-label="Toggle navigation">
                <i class="bi bi-list-nested"></i>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mr-auto">

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <@spring.message'layout.dropdown'/>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="?lang=ru"><@spring.message'layout.ru'/></a></li>
                            <li><a class="dropdown-item" href="?lang=en"><@spring.message'layout.en'/></a></li>
                        </ul>
                    </li>

                    <#if !known>
                        <li class="nav-item">
                            <a href="/auth/login" class="btn btn-primary rounded-1"><@spring.message'layout.login'/></a>
                        </li>
                        <li class="nav-item">
                            <a href="/auth/register" class="btn btn-primary rounded-1"><@spring.message'layout.register'/></a>
                        </li>
                    </#if>

                    <#if known>
                        <li class="nav-item">
                            <a href="/profile" class="btn btn-primary rounded-1"><@spring.message'layout.profile'/></a>
                        </li>
                        <li class="nav-item">
                            <a href="/logout" class="btn btn-primary rounded-1" id="logout"><@spring.message'layout.logout'/></a>
                        </li>
                        <li class="nav-item">
                            <a href="/vacancies" class="btn btn-primary rounded-1"><@spring.message'layout.vacancies'/></a>
                        </li>
                        <#assign
                        user = SPRING_SECURITY_CONTEXT.authentication.principal
                        roles = user.authorities
                        >
                        <#if roles?seq_contains("employer")>
                            <li class="nav-item">
                                <a href="/resumes" class="btn btn-primary rounded-1"><@spring.message'layout.resumes'/></a>
                            </li>
                        </#if>
                    </#if>
                </ul>

                <ul class="navbar-nav ml-auto">
                    <#if known>
                        <#assign
                        user = SPRING_SECURITY_CONTEXT.authentication.principal
                        name = user.getUsername()
                        >
                        <li class="nav-item">
                            <span class="navbar-text text-light"><@spring.message'layout.hello'/>  ${name}</span>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>

    <main class="container mt-4">
        <div class="row">
            <#nested spring>
        </div>
    </main>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>

</#macro>
