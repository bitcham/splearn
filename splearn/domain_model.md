# Splearn Domain Model

## Creating a Domain Model
1. Listen and learn
2. Find the 'important things' (identify concepts)
3. Find the 'links' (define relationships)
4. Describe the 'things' (specify attributes and basic behaviors)
5. Visualize (draw it out)
6. Discuss and refine (iterate)

## Splearn Domain
- Splearn is an online service where members take courses.
- Splearn aims to develop and operate a learning ecosystem based on the philosophy of the Spring Framework.
  The core component of this ecosystem is the member who learns (grows).
    - That's why the name is Spring + Learner = Splearn
    - There is a similar name, Inflearn.
- To participate in Splearn, you must register as a member.
    - However, even before becoming a member, you can view information about Splearn and its courses.
    - To take a course, you must complete registration and become an active member.
    - After applying for registration and meeting the required conditions, registration is completed.
    - Once registered, you can register or edit your profile URL and self-introduction.
        - The profile URL must be a unique value within 15 characters, consisting of letters and numbers.
        - The profile URL and self-introduction of withdrawn members cannot be modified.
    - Registration time, registration completion time, and withdrawal time are stored.
- Members who wish can also become instructors who provide their knowledge and experience to other members in the form of courses.
    - To become an instructor for the first time, a member must go through an application and approval process.
    - Approved instructors can create courses and make them public after review.
    - Published courses are exposed to members, and members can take these courses.
- Enrollment means learning a course. "The course I have taken is a. I am currently taking course b."
    - To enroll, you must first apply for enrollment.
    - Depending on the course requirements, you may need to apply for enrollment and additional procedures may be required.
    - Some courses can be taken immediately upon enrollment application.
    - For some courses, you must complete the course fee payment after applying for enrollment to take the course.
    - Instructors may set limits on the course enrollment period.
- A course consists of one or more lessons containing content such as videos and documents.
    - There can be many lessons, so lessons are further divided into sections.
    - A course consists of multiple sections and lessons belonging to each section.
    - Lessons and sections have a learning order.
    - Learning progress is recorded for each lesson.
    - When you complete all lessons in a course and reach 100% progress, you have completed the course.

## Domain Model

---
### [Member Aggregate]

### Member
_Aggregate Root_
#### Attributes
- `id`: `Long`
- `email`: Email - Natural ID
- `nickname`: Nickname
- `passwordHash`: Password hash
- `status`: `MemberStatus` Member status
- `detail`: `MemberDetail` 1:1
#### Behaviors
- `static register()`: Register member: email, nickname, password, passwordEncoder
- `activate()`: Complete registration
- `deactivate()`: Withdraw member
- `verifyPassword()`: Verify password
- `changePassword()`
- `updateInfo()`: Edit member information. Edit nickname, profile URL, and self-introduction
#### Rules
- After member creation, status is pending
- Registration is completed when certain conditions are met
- Only pending members can complete registration
- Only active members can withdraw
- Only active members can edit their information
- Member passwords are stored as hashes
- Profile URLs must be unique. Existing profile URLs can be removed

### Member Status
_Enum_
#### Constants
- `PENDING`: Pending registration
- `ACTIVE`: Registration completed
- `DEACTIVATED`: Withdrawn
### Member Detail
  _Entity_
- `id`: `Long`
- `profile`: Profile URL. All members must have a unique profile URL
- `introduction`: Self-introduction
- `registeredAt`: Registration date and time
- `activatedAt`: Registration completion date and time
- `deactivatedAt`: Withdrawal date and time
#### Behaviors
- `static create()`: Register member. Stores the current time as registration date and time.
- `activate()`: Perform tasks related to registration completion. Store registration completion date and time.
- `deactivate()`: Perform tasks related to withdrawal. Store withdrawal date and time.
- `updateInfo()`: Edit detailed information


### DuplicateEmailException
_Exception_

### Password Encoder
_Domain Service_
#### Behaviors
- `encode()`: Encrypt password
- `matches()`: Check if password matches

### Profile
_Value Object_
#### Attributes
- `address`: Profile URL

---

### Email
_Value Object_
#### Attributes
- `address`: Email address
---

### Instructor

### Course

### Lesson

### Section

### Enrollment

### Progress
