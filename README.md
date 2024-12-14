# Project - Eepy
Project Eepy is a project that helps individuals achieve good sleep quality for their productivity.

---

### Team ID : C242-PS297

### Team Members :
1. (ML) M120B4KY0147 – Afad Fath Musyarof Halim – Institut Teknologi Telkom Purwokerto 
2. (ML) M771B4KX0662 – Armina Zebua – Universitas PGRI Delta Sidoarjo 
3. (ML) M007B4KY1147 – Dimas Narendra Putra – Universitas Dian Nuswantoro 
4. (CC) C120B4KY4254 – Syahrul Zaki Khuzaini – Institut Teknologi Telkom Purwokerto 
5. (CC) C120B4KY1455 – Fauzan Wahyu Mubarak – Institut Teknologi Telkom Purwokerto 
6. (MD) A120B4KY0720 – Aufa Muhammad Isyfa’Lana – Institut Teknologi Telkom Purwokerto 
7. (MD) A120B4KY1318 –  Fadhila Agil Permana – Institut Teknologi Telkom Purwokerto 


#### Theme for Project : Health Innovation: Empowering Vulnerable Communities for Health and Well-being

### Machine Learning Path


### Mobile Development Path
#### **Steps for Android Application Development**

1. **UI/UX Design**
   - Designed the application's wireframe using **Figma**.
   - Implemented the design in XML layouts for Android.

2. **Core Features Development**
   - Developed a **survey page** to collect user inputs such as age, gender, physical activity, stress level, and sleep history.
   - Built **API integration** using **Retrofit** to connect with the machine learning model deployed via Cloud Run.
   - Created a **sleep trends visualization** feature using **MPAndroidChart** for bar charts.
   - Created a swipeable **Tips & Trick** feature using **Viewpager2** 

3. **Navigation Implementation**
   - Designed smooth navigation between survey fragments to home page.

4. **Local Data Storage**
   - Used **SharedPreferences** to save and retrieve user data such as survey inputs and API predictions.

5. **Tips & Tricks**
   - Integrated a **Tips & Tricks** feature with a swipeable interface to educate users on improving sleep quality.

6. **Testing and Debugging**
   - Conducted rigorous testing using real user data.
   - Tested API responses and handled edge cases effectively.

7. **Application Build**
   - Branded the application with a custom **icon** from the drawable resources.
   - Built and signed the APK for deployment.

### **Tools/Package Used**
- **Android Studio**: For application development and debugging.
- **Retrofit**: For API integration with the Cloud Run services.
- **MPAndroidChart**: For rendering sleep trends as bar charts.
- **Postman**: For API testing and validation.
- **Figma**: For UI/UX design and wireframes.

### **Key Features**
- **Daily Survey**: Users fill out a survey to track their sleep habits and receive predictions.
- **Sleep Trends Visualization**: Graphs displaying historical sleep data.
- **Tips & Tricks**: Educational content to improve sleep quality.
- **Sleep Analysis**: Ideal sleep duration and quality predictions from the machine learning model.


### Cloud Computing Path

#### Steps for Cloud Run Deployment  
1. Clone the GitHub repository from the Machine Learning path.  
2. Open Google Cloud Platform.  
3. Create a new project with a name matching your project.  
4. Open the Cloud Run service.  
5. Configure the settings as needed. Our Cloud Computing Team uses the GitHub repository clone method.  
6. Wait for the deployment process to complete.  
7. Once completed, test the API using Postman with the required input.  
8. Check the results on Postman.  

### Cloud Architecture
![Langkah_SS](/CC/Document/Architecture%20Cloud.png)

### Tools Used
- [Google Cloud Platform](https://cloud.google.com/)
- [Postman](https://www.postman.com/)
- [Google Pricing Calculator](https://cloud.google.com/products/calculator)


