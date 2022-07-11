This project is built off of a BDD framework which uses Selenium with Cucumber. The Scenarios are written into the feature
files using the Gherkin language, which allows the technical and non-technical sides of the company to have a common
understanding. Each scenario has many steps which get implement into a steps class which is where I write the actual code.
I utilized Page Object Model by creating a page class for each HTML page, and this is where I store all my Web Elements.
I have implemented the Singleton design pattern so that there is only one instance of a driver. I also use a runner class
to execute my scenarios. We can execute specific suites by utilizing its tag in my runner class or by running a maven 
command in the terminal.