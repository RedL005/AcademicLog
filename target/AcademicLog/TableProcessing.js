function validateGrade(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if ( (charCode < 50 || charCode > 53) && (charCode < 98 || charCode > 101))
            return false;

         return true;
      }