package com.coursesPlatform.coursePortfolio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByCustomerMail(String mail);

    Optional<Offer> findByOfferId(String offerId);
}
