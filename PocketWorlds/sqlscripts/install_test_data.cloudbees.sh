## this script creates the data required for a minimal test of the pocketworlds application 
MYSQL_COMMAND_LINE="mysql --host=us-cdbr-cb-east-01.cleardb.net --user=bebc27f652c734 --password=1db821fc cb_pocketworlds"

$MYSQL_COMMAND_LINE << EOF
SET autocommit = 0;
SELECT '1 : CLEANING DATABASE ...' AS OPERATION , CURRENT_TIMESTAMP AS START_DATE;
source ./database_creation_scripts/delete_database_structure.sql
COMMIT;
EOF
echo ' '
echo ' '

$MYSQL_COMMAND_LINE << EOF
SET autocommit = 0;
SELECT '2 : CREATING DATABASE STRUCTURE ...' AS OPERATION , CURRENT_TIMESTAMP AS START_DATE;
source ./database_creation_scripts/create_database_structure.sql
COMMIT;
EOF
echo ' '
echo ' '

$MYSQL_COMMAND_LINE << EOF
SET autocommit = 0;
SELECT '3 : DATA INSERTION ...', CURRENT_TIMESTAMP;
source ./test_data/create_test_data.sql
COMMIT;
EOF
echo ' '
echo ' '
