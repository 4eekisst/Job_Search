<#import "../layout.ftlh" as profile>
<@profile.layout>

    <div class="profile-container">
        <#if user.authority_id == 1>
            <h2>Личный кабинет соискателя</h2>
        <#else>
            <h2>Личный кабинет работодателя</h2>
        </#if>

        <div class="card border-0">
            <#if user.avatar??>
                <img src="api/users/image/${user.avatar}" class="avatar-img" alt="User image" style="border-radius: 30%; width: 300px;
                        height: 250px;
                        object-fit: cover;
                        cursor: pointer;
                        margin: 0 auto;
                        transition: transform 0.2s ease-in-out; ">
            <#else >
                <img src="api/images/image/anon.jpeg" class="avatar-img" alt="User image" style="border-radius: 30%; width: 300px;
                        height: 250px;
                        object-fit: cover;
                        cursor: pointer;
                        margin: 0 auto;
                        transition: transform 0.2s ease-in-out; ">
            </#if>
        </div>

        <div class="profile-card">
            <h3>${user.name} ${user.surname!}</h3>
            <p><strong>Email:</strong> ${user.email}</p>
            <p><strong>Age:</strong> ${user.age}</p>
            <p><strong>Phone Number:</strong> ${user.phoneNumber!}</p>
        </div>
    </div>

</@profile.layout>
