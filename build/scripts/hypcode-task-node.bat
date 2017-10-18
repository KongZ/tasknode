@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  hypcode-task-node startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and HYPCODE_TASK_NODE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Dlog4j.configurationFile=./conf/log4j2.xml"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\hypcode-task-node-0.0.1.jar;%APP_HOME%\lib\jsp-api-2.2.jar;%APP_HOME%\lib\jackson-core-2.8.8.jar;%APP_HOME%\lib\jackson-databind-2.8.8.jar;%APP_HOME%\lib\jackson-annotations-2.8.8.jar;%APP_HOME%\lib\redisson-3.5.4.jar;%APP_HOME%\lib\guava-21.0.jar;%APP_HOME%\lib\jersey-container-grizzly2-http-2.26.jar;%APP_HOME%\lib\jersey-container-grizzly2-servlet-2.26.jar;%APP_HOME%\lib\jersey-media-moxy-2.26.jar;%APP_HOME%\lib\jersey-media-json-jackson-2.26.jar;%APP_HOME%\lib\jersey-hk2-2.26.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\log4j-core-2.9.1.jar;%APP_HOME%\lib\log4j-slf4j-impl-2.9.1.jar;%APP_HOME%\lib\log4j-1.2-api-2.9.1.jar;%APP_HOME%\lib\slf4j-log4j12-1.7.25.jar;%APP_HOME%\lib\netty-common-4.1.15.Final.jar;%APP_HOME%\lib\netty-codec-4.1.15.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.15.Final.jar;%APP_HOME%\lib\netty-transport-4.1.15.Final.jar;%APP_HOME%\lib\netty-handler-4.1.15.Final.jar;%APP_HOME%\lib\cache-api-1.0.0.jar;%APP_HOME%\lib\reactor-stream-2.0.8.RELEASE.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.7.9.jar;%APP_HOME%\lib\zero-allocation-hashing-0.8.jar;%APP_HOME%\lib\byte-buddy-1.6.14.jar;%APP_HOME%\lib\jodd-bean-3.7.1.jar;%APP_HOME%\lib\javax.inject-2.5.0-b42.jar;%APP_HOME%\lib\grizzly-http-server-2.4.0.jar;%APP_HOME%\lib\jersey-common-2.26.jar;%APP_HOME%\lib\jersey-server-2.26.jar;%APP_HOME%\lib\javax.ws.rs-api-2.1.jar;%APP_HOME%\lib\jersey-container-servlet-2.26.jar;%APP_HOME%\lib\grizzly-http-servlet-2.4.0.jar;%APP_HOME%\lib\jersey-entity-filtering-2.26.jar;%APP_HOME%\lib\org.eclipse.persistence.moxy-2.6.4.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.8.4.jar;%APP_HOME%\lib\hk2-locator-2.5.0-b42.jar;%APP_HOME%\lib\log4j-api-2.9.1.jar;%APP_HOME%\lib\netty-resolver-4.1.15.Final.jar;%APP_HOME%\lib\reactor-core-2.0.8.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.15.jar;%APP_HOME%\lib\jodd-core-3.7.1.jar;%APP_HOME%\lib\grizzly-http-2.4.0.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.1.jar;%APP_HOME%\lib\jersey-client-2.26.jar;%APP_HOME%\lib\jersey-media-jaxb-2.26.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.26.jar;%APP_HOME%\lib\org.eclipse.persistence.core-2.6.4.jar;%APP_HOME%\lib\javax.json-1.0.4.jar;%APP_HOME%\lib\aopalliance-repackaged-2.5.0-b42.jar;%APP_HOME%\lib\hk2-api-2.5.0-b42.jar;%APP_HOME%\lib\hk2-utils-2.5.0-b42.jar;%APP_HOME%\lib\javassist-3.22.0-CR2.jar;%APP_HOME%\lib\reactive-streams-1.0.0.jar;%APP_HOME%\lib\grizzly-framework-2.4.0.jar;%APP_HOME%\lib\org.eclipse.persistence.asm-2.6.4.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\javax.servlet-api-4.0.0-b07.jar

@rem Execute hypcode-task-node
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %HYPCODE_TASK_NODE_OPTS%  -classpath "%CLASSPATH%" co.hypcode.tasknode.Application %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable HYPCODE_TASK_NODE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%HYPCODE_TASK_NODE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
