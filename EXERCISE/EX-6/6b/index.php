<?php
// Load the XML file
$xml = simplexml_load_file('tourism.xml') or die("Error: Cannot create object");

// Iterate through the XML structure and display data
foreach ($xml->destination as $destination) {
    echo "<h2>Destination: " . $destination->name . "</h2>";
    echo "<p>Country: " . $destination->country . "</p>";
    echo "<h3>Attractions:</h3><ul>";
    foreach ($destination->attractions->attraction as $attraction) {
        echo "<li>" . $attraction . "</li>";
    }
    echo "</ul><hr>";
}
?>
