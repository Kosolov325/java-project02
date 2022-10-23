from rest_framework import routers
from .views import usersView

router = routers.SimpleRouter()
router.register('users', usersView)

urlpatterns = router.urls
