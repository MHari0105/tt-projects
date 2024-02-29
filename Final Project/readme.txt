Users Collection:
Store user information such as first name, last name, email, gender, date of birth, pincode, role, profession, and hashed password.
This collection will include users from all roles: public, workers, higher authority, and admin.

Posts Collection:
Store posts created by users, including the post description, image(s), user ID of the creator, timestamp, and post status.
This collection will also store the severity level of each post and the issue cleared status (pending/work in progress/solved).

Comments Collection:
Store comments made by users on posts, including the comment text, user ID of the commenter, post ID, and timestamp.

Tasks Collection:
Store tasks assigned by higher authority to workers, including the task description, worker ID, timestamp, and task status.

Ratings Collection:
Store ratings given by public users for the work done by workers, including the worker ID, user ID of the rater, rating value, and timestamp.

AdminVerificationRequests Collection:
Store registration requests from users with the role of authority, including the user ID, timestamp, and verification status.

ReportedPosts Collection:
Store reported posts along with the number of times they've been reported, the user IDs of the reporters, and the timestamp.

DeletedPosts Collection:
Store posts deleted by the admin, including the post ID, reason for deletion, user ID of the admin, and timestamp.

PincodeMapping Collection:
Store mapping of pin codes to users, ensuring that users can only view posts from users in the same pin code area.