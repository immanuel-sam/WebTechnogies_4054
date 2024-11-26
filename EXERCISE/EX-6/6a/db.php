<?php
// Database connection details
$host = 'localhost';  // or your database host
$dbname = 'tourism_db';
$username = 'root';   // your database username
$password = '';       // your database password

try {
    // Establish connection
    $conn = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    // Insert data into the database (example)
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $name = $_POST['name'];
        $email = $_POST['email'];
        $feedback = $_POST['feedback'];

        // Insert query
        $stmt = $conn->prepare("INSERT INTO tourist_info (name, email, feedback) VALUES (?, ?, ?)");
        $stmt->execute([$name, $email, $feedback]);
        echo "Feedback submitted successfully!";
    }

    // Retrieve data from the database (example)
    $stmt = $conn->query("SELECT * FROM tourist_info");
    $rows = $stmt->fetchAll();

    echo "<h2>Feedback List:</h2>";
    foreach ($rows as $row) {
        echo "<p><strong>Name:</strong> " . $row['name'] . "<br><strong>Email:</strong> " . $row['email'] . "<br><strong>Feedback:</strong> " . $row['feedback'] . "</p><hr>";
    }
} catch (PDOException $e) {
    echo "Connection failed: " . $e->getMessage();
}
?>

<!-- HTML Form for inserting data -->
<form method="POST" action="">
    <label for="name">Name:</label>
    <input type="text" name="name" required><br><br>
    <label for="email">Email:</label>
    <input type="email" name="email" required><br><br>
    <label for="feedback">Feedback:</label>
    <textarea name="feedback" required></textarea><br><br>
    <input type="submit" value="Submit">
</form>
