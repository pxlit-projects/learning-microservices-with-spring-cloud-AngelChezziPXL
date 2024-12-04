const users = [
    {
        username: "user",
        password: "password",
        roles: ["user"],
    },
    {
        username: "admin",
        password: "password",
        roles: ["admin"],
    },
];

let token = null; // Global token variable to store the current user's token

export default class AuthService {
    static async sendLoginRequest(username, password) {
        console.log(username + " " + password);
        const foundUser = users.find(
            (u) => u.username === username && u.password === password
        );

        if (foundUser) {
            // Simulate a token (could be a string combining username and roles)
            token = `Bearer ${btoa(`${foundUser.username}:${foundUser.roles.join(",")}`)}`;

            // Return user details and token
            return {
                username: foundUser.username,
                roles: foundUser.roles,
                token,
            };
        } else {
            throw new Error("Invalid username or password");
        }
    }

    static async sendLogoutRequest() {
        try {
            token = null; // Clear the token
            console.log("User logged out, token cleared.");
            return { success: true };
        } catch (error) {
            console.error(error);
            throw error;
        }
    }

    static getAuthorizationHeader() {
        if (!token) {
            throw new Error("No token available. Please log in.");
        }

        // Return the Authorization header
        return { Authorization: token };
    }
}
