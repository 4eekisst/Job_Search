document.addEventListener("DOMContentLoaded", function() {
    let workExperienceCount = 1;
    let educationCount = 1;
    let contactInfoCount = 5;

    document.getElementById("addWorkExperience").addEventListener("click", function() {
        const workExperienceSection = document.getElementById("workExperienceSection");
        const newWorkExperience = `
            <div class="work-experience-block mb-3" id="workExperience_${workExperienceCount}">
                <div class="form-group">
                    <label for="years_${workExperienceCount}">${window.messages.resumeCreateYear}</label>
                    <input type="text" class="form-control" name="workExperienceInfoDtos[${workExperienceCount}].years">
                </div>
                <div class="form-group">
                    <label for="companyName_${workExperienceCount}">${window.messages.resumeCreateCompanyName}</label>
                    <input type="text" class="form-control" name="workExperienceInfoDtos[${workExperienceCount}].companyName">
                </div>
                <div class="form-group">
                    <label for="position_${workExperienceCount}">${window.messages.resumeCreatePosition}</label>
                    <input type="text" class="form-control" name="workExperienceInfoDtos[${workExperienceCount}].position">
                </div>
                <div class="form-group">
                    <label for="responsibilities_${workExperienceCount}">${window.messages.resumeCreateResponsibilities}</label>
                    <textarea class="form-control" name="workExperienceInfoDtos[${workExperienceCount}].responsibilities"></textarea>
                </div>
                <button type="button" class="btn btn-danger remove-button" data-target="workExperience_${workExperienceCount}">${window.messages.resumeCreateRemove}</button>
            </div>
        `;
        workExperienceSection.insertAdjacentHTML('beforeend', newWorkExperience);
        workExperienceCount++;
    });

    document.getElementById("addEducation").addEventListener("click", function() {
        const educationSection = document.getElementById("educationSection");
        const newEducation = `
            <div class="education-block mb-3" id="education_${educationCount}">
              <div class="form-group">
                    <label for="institution_${educationCount}">${window.messages.resumeCreateInstitution}</label>
                    <input type="text" class="form-control" name="educationInfoDtos[${educationCount}].institution">
                </div>
                <div class="form-group">
                    <label for="degree_${educationCount}">${window.messages.resumeCreateDegree}</label>
                    <input type="text" class="form-control" name="educationInfoDtos[${educationCount}].degree">
                </div>
                <div class="form-group">
                    <label for="program_${educationCount}">${window.messages.resumeCreateProgram}</label>
                    <input type="text" class="form-control" name="educationInfoDtos[${educationCount}].program">
                </div>
                <div class="form-group">
                    <label for="startDate_${educationCount}">${window.messages.resumeCreateStartDate}</label>
                    <input type="date" class="form-control" name="educationInfoDtos[${educationCount}].startDate">
                </div>
                <div class="form-group">
                    <label for="endDate_${educationCount}">${window.messages.resumeCreateEndDate}</label>
                    <input type="date" class="form-control" name="educationInfoDtos[${educationCount}].endDate">
                </div>
                <button type="button" class="btn btn-danger remove-button" data-target="education_${educationCount}">${window.messages.resumeCreateRemove}</button>
            </div>
        `;
        educationSection.insertAdjacentHTML('beforeend', newEducation);
        educationCount++;
    });

    document.getElementById("addContactInfo").addEventListener("click", function() {
        const contactInfoSection = document.getElementById("contactInfoSection");
        const newContactInfo = `
        <div class="contact-info-block mb-3" id="contactInfo_${contactInfoCount}">
            <div class="form-group">
                <label for="telegram_${contactInfoCount}">Telegram</label>
                <input type="text" class="form-control" name="contactInfoDtos[${contactInfoCount}].value" placeholder="${window.messages.resumeTelegramPlaceHolder}">
                <input type="hidden" name="contactInfoDtos[${contactInfoCount}].typeId" value="1">
            </div>
            <div class="form-group">
                <label for="email_${contactInfoCount}">${window.messages.resumeCreateEmail}</label>
                <input type="email" class="form-control" name="contactInfoDtos[${contactInfoCount + 1}].value" placeholder="${window.messages.resumeCreateEmailPlaceHolder}">
                <input type="hidden" name="contactInfoDtos[${contactInfoCount + 1}].typeId" value="2">
            </div>
            <div class="form-group">
                <label for="phoneNumber_${contactInfoCount}">${window.messages.resumeCreatePhoneNumber}</label>
                <input type="text" class="form-control" name="contactInfoDtos[${contactInfoCount + 2}].value" placeholder="${window.messages.resumeCreatePhonePlaceHolder}">
                <input type="hidden" name="contactInfoDtos[${contactInfoCount + 2}].typeId" value="3">
            </div>
            <div class="form-group">
                <label for="facebook_${contactInfoCount}">Facebook</label>
                <input type="text" class="form-control" name="contactInfoDtos[${contactInfoCount + 3}].value" placeholder="${window.messages.resumeCreateFacebookPlaceHolder}">
                <input type="hidden" name="contactInfoDtos[${contactInfoCount + 3}].typeId" value="4">
            </div>
            <div class="form-group">
                <label for="linkedin_${contactInfoCount}">LinkedIn</label>
                <input type="text" class="form-control" name="contactInfoDtos[${contactInfoCount + 4}].value" placeholder="${window.messages.resumeCreateLinkedInPlaceHolder}">
                <input type="hidden" name="contactInfoDtos[${contactInfoCount + 4}].typeId" value="5">
            </div>
            <button type="button" class="btn btn-danger remove-button" data-target="contactInfo_${contactInfoCount}">${window.messages.resumeCreateRemove}</button>
        </div>
    `;
        contactInfoSection.insertAdjacentHTML('beforeend', newContactInfo);
        contactInfoCount += 5;
    });

    document.addEventListener("click", function(event) {
        if (event.target.classList.contains("remove-button")) {
            const targetId = event.target.getAttribute("data-target");
            const targetElement = document.getElementById(targetId);
            if (targetElement) {
                targetElement.remove();
            }
        }
    });
});
