# Splearn Domain Model

## Splearn Domain
* Splearn is an online service where members take courses.
* Splearn aims to be a learning ecosystem that is developed, operated, and evolved based on the philosophy of the Spring Framework.
  This ecosystem's core component is the learning (growing) members.
  * That's why the name is Spring + Learner = Splearn
  * A similar name is Inflearn.
* To participate in Splearn, you must register as a member.
  * However, even before becoming a member, you can explore introductions about Splearn and course information.
  * To take courses, you must complete registration and become an active member.
  * After submitting a registration application, registration is completed when specified requirements are met.
  * Once registration is completed, you can register or modify your profile URL and self-introduction.
    * Profile URL consists of alphabets and numbers within 15 characters and must be unique.
    * There may be restrictions on using profile URLs and self-introductions from withdrawn members.
  * Course viewing, course enrollment, and withdrawal are supported.
* A withdrawn member is a member who was once active but is no longer active due to their own request.
  * A withdrawn member's profile becomes invisible and their profile information is anonymized.
  * Withdrawn members cannot be lectures and comments.
  * Withdrawn members cannot create courses or enroll in new courses.
* Courses consist of one or more lessons with content such as videos and documents.
  * Since there can be many lessons, lessons are organized into sections.
    * Course enrollment is recorded for each member per course, enabling progress tracking.
    * Course enrollment becomes active when a member enrolls and inactive when they withdraw.
    * One member can have multiple course enrollments simultaneously.
    * Members can check the list of lessons they haven't taken yet.
* Courses consist of multiple sections and lessons belonging to each section.
  * A lesson has a specific learning order within a section.
  * When a section is created, it can be placed anywhere in the course.
  * Section progress is calculated based on the progress of lessons within the section.
  * Course progress is calculated based on section progress.
* Learning progress is recorded for each lesson.
  * Lessons have videos, documents, and various content formats for learning.
  * A lesson may have content that spans multiple pages.
  * The progress of each lesson with multiple pages should be tracked.
  * Each lesson records learning progress for each member.
  * Learning completion is achieved by finishing all lessons in a course and reaching 100% progress.

## Domain Model

### Member
_Entity_
#### Attributes
- `email` : ID
- `nickname`
- `passwordHash`
- `status`
#### Behaviors
- `companion object register()`: MemberRegisterRequest, status
- `activate()`
- `deactivate()`
- `verifyPassword()`
- `changeNickname()`
- `changePassword()`
#### Rules
- After user register, status is set to pending 
- When requirements are met, status changes to active
- Only when status is pending, activation is possible
- Only when status is active, deactivation is possible
- User's password is stored as a hash value

### MemberRegisterRequest
_DTO_
#### Attributes
- `email`
- `nickname`
- `password`

### Email
_Value Object_
#### Behaviors
- `of()` : validation

### MemberStatus
_Enum_
#### Constants
- PENDING
- ACTIVE
- DEACTIVATED: withdrawn member

### PasswordEncoder
_Domain Service_
#### Behaviors
- `encode()`: returns passwordHash
- `matches()`: returns boolean

### Instructor 

### Course

### Lesson

### Section

### Enrollment

### Progress


