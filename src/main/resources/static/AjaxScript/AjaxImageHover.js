$('.popover-img').on({
    mousemove: function(e) {
        $(this).next('img').css({
            left: e.pageX + 300
        });
    },
    mouseenter: function() {
        let big = $('<img />', {'class': 'big_img', src: this.src});
        $(this).after(big);
    },
    mouseleave: function() {
        $('.big_img').remove();
    }
});
