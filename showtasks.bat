call runcrud.bat

if "%ERRORLEVEL%" == "0" goto search
echo.
echo gradle build has errors - breaking work
goto fail

:search
echo.
start chrome "http://localhost:8080/v1/task/getTasks"
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished