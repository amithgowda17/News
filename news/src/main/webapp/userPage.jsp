<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page isELIgnored="false" %>

        <html>

        <head>
            <title>Dashboard</title>
            <link rel="icon" href="https://www.x-workz.in/Logo.png">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
            <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
        </head>

        <body>

            <header class="bg-dark text-white text-center py-3">
                <div class="d-flex justify-content-between align-items-center">
                    <img src="https://www.x-workz.in/Logo.png" alt="Logo" style="max-height: 50px;">




                    <div class="dropdown mb-1">
                        <img src="getImage/${details.fileName}" class="rounded-circle mx-2" alt="img" width="90"
                            data-bs-toggle="modal" data-bs-target="#imageModal" />
                        <a class="btn btn-sm btn-secondary dropdown-toggle dropdown-toggle-split mx-2" href=""
                            role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                            ${details.fname}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="${details.fname}">
                            <a class="dropdown-item" href="profileUpdate?email=${details.email}">Profile</a>
                            <a class="dropdown-item" href="news">News</a>
                            <a class="dropdown-item" href="loginPage">Log out</a>

                        </div>
                    </div>
                </div>
            </header>


            <div class="modal fade" id="imageModal" tabindex="-1" aria-labelledby="imageModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="imageModalLabel">Profile Picture</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body text-center">
                            <img src="getImage/${details.fileName}" class="img-fluid" alt="Profile Image" />
                        </div>
                    </div>
                </div>
            </div>


  <div class="container mt-5">
    <div class="row">

      <!-- News Card 1 -->
      <div class="col-md-6 mb-4">
        <div class="card h-100">
          <img src="https://images.unsplash.com/photo-1518770660439-4636190af475?ixlib=rb-4.0.3&auto=format&fit=crop&w=870&q=80"
               class="card-img-top img-fluid" alt="Tech News">
          <div class="card-body">
            <h5 class="card-title">Tech Startups Lead AI Innovation</h5>
            <p class="card-text">
              Emerging startups are driving the next wave of AI breakthroughs, challenging big tech and reshaping software development.
            </p>
            <a href="https://www.startupindia.gov.in/content/sih/en/bloglist/blogs/7_leading_AI_startups_in_India.html" target="_blank" class="btn btn-primary">Read More</a>
          </div>
        </div>
      </div>

      <!-- News Card 2 -->
      <div class="col-md-6 mb-4">
        <div class="card h-100">
          <img src="https://images.unsplash.com/photo-1504384308090-c894fdcc538d?ixlib=rb-4.0.3&auto=format&fit=crop&w=870&q=80"
               class="card-img-top img-fluid" alt="Environment News">
          <div class="card-body">
            <h5 class="card-title">Climate Action Takes Center Stage</h5>
            <p class="card-text">
              Global leaders meet to set new carbon goals and discuss innovative strategies to combat climate change and promote sustainability.
            </p>
            <a href="https://www.nationalgeographic.com/environment/article/climate-change" target="_blank" class="btn btn-primary">Read More</a>
          </div>
        </div>
      </div>

    </div>
  </div>





          <footer class="bg-dark text-white text-center py-3 fixed-bottom border-top">
              <div class="container-fluid">
                <p class="mb-0">Copyright &copy; 2025, All Rights Reserved</p>
              </div>
            </footer>
        </body>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
            integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
            crossorigin="anonymous"></script>

        </html>